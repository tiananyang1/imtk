package com.intercom.input.gallery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.intercom.composer.ImageLoader;
import com.intercom.composer.input.InputFragment;
import com.intercom.input.gallery.GalleryInputDataSource;
import com.intercom.input.gallery.adapter.EndlessRecyclerScrollListener;
import com.intercom.input.gallery.adapter.EndlessScrollListener;
import com.intercom.input.gallery.adapter.GalleryRecyclerViewAdapter;
import com.intercom.input.gallery.adapter.OnImageClickListener;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInputFragment.class */
public abstract class GalleryInputFragment extends InputFragment implements GalleryInputScreen, OnImageClickListener, EndlessScrollListener {
    private static final String ARG_EXPANDED = "expanded";
    public static final int GALLERY_FULL_SCREEN_REQUEST_CODE = 14;

    @VisibleForTesting
    FrameLayout contentLayout;

    @VisibleForTesting
    GalleryInputDataSource dataSource;

    @VisibleForTesting
    EmptyView emptyLayout;

    @VisibleForTesting
    EndlessRecyclerScrollListener endlessRecyclerScrollListener;

    @VisibleForTesting
    boolean expanded;

    @VisibleForTesting
    @Nullable
    GalleryInputExpandedListener galleryInputExpandedListener;

    @VisibleForTesting
    @Nullable
    GalleryOutputListener galleryOutputListener;
    private ImageLoader imageLoader;

    @VisibleForTesting
    Injector injector;

    @VisibleForTesting
    LinearLayoutManager layoutManager;

    @VisibleForTesting
    GalleryRecyclerViewAdapter recyclerAdapter;

    @VisibleForTesting
    RecyclerView recyclerView;

    @VisibleForTesting
    final List<GalleryImage> galleryImages = new ArrayList();

    @VisibleForTesting
    final GalleryInputDataSource.Listener dataListener = new GalleryInputDataSource.Listener() { // from class: com.intercom.input.gallery.GalleryInputFragment.1
        @Override // com.intercom.input.gallery.GalleryInputDataSource.Listener
        public void onError() {
            if (GalleryInputFragment.this.isAdded()) {
                GalleryInputFragment.this.showErrorScreen();
            }
        }

        @Override // com.intercom.input.gallery.GalleryInputDataSource.Listener
        public void onSuccess(List<GalleryImage> list) {
            GalleryInputFragment.this.galleryImages.clear();
            GalleryInputFragment.this.galleryImages.addAll(list);
            GalleryInputFragment.this.endlessRecyclerScrollListener.setMaxCount(GalleryInputFragment.this.dataSource.getCount());
            GalleryInputFragment.this.recyclerAdapter.notifyDataSetChanged();
            if (GalleryInputFragment.this.isAdded()) {
                GalleryInputFragment.this.showEmptyOrPermissionScreen(0);
            }
        }
    };
    private final View.OnClickListener expanderClickListener = new View.OnClickListener() { // from class: com.intercom.input.gallery.GalleryInputFragment.2
        @Override // android.view.View.OnClickListener
        @SensorsDataInstrumented
        public void onClick(View view) {
            if (GalleryInputFragment.this.galleryInputExpandedListener != null) {
                GalleryInputFragment.this.galleryInputExpandedListener.onInputExpanded();
            }
            GalleryInputFragment.this.startActivityForResult(GalleryInputFullScreenActivity.createIntent(GalleryInputFragment.this.getActivity(), GalleryInputFragment.this.getClass(), GalleryInputFragment.this.getArguments()), 14, ActivityOptionsCompat.makeCustomAnimation(GalleryInputFragment.this.getActivity(), C0134R.anim.intercom_composer_slide_up, C0134R.anim.intercom_composer_stay).toBundle());
            SensorsDataAutoTrackHelper.trackViewOnClick(view);
        }
    };

    /* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryInputFragment$Injector.class */
    public interface Injector {
        GalleryInputDataSource getDataSource(GalleryInputFragment galleryInputFragment);

        String getEmptyViewSubtitle(Resources resources);

        String getEmptyViewTitle(Resources resources);

        String getErrorViewSubtitle(Resources resources);

        String getErrorViewTitle(Resources resources);

        @Nullable
        View getExpanderButton(ViewGroup viewGroup);

        ImageLoader getImageLoader(GalleryInputFragment galleryInputFragment);

        Class<? extends GalleryLightBoxFragment> getLightBoxFragmentClass(GalleryInputFragment galleryInputFragment);

        @Nullable
        View getSearchView(ViewGroup viewGroup);

        @ColorInt
        int getThemeColor(Context context);

        Toolbar getToolbar(ViewGroup viewGroup);
    }

    public static Bundle createArguments(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_EXPANDED, z);
        return bundle;
    }

    private void setUpAppBar(Injector injector, ViewGroup viewGroup) {
        Toolbar toolbar = injector.getToolbar(viewGroup);
        viewGroup.addView(toolbar);
        getActivity().setSupportActionBar(toolbar);
        ActionBar supportActionBar = getActivity().getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setUpPreviewViews(Injector injector, ViewGroup viewGroup) {
        ImageButton imageButton = (ImageButton) injector.getExpanderButton(this.contentLayout);
        if (imageButton != null) {
            this.contentLayout.addView(imageButton);
            imageButton.setOnClickListener(this.expanderClickListener);
        }
        View searchView = injector.getSearchView(this.contentLayout);
        if (searchView != null) {
            searchView.setOnClickListener(this.expanderClickListener);
            viewGroup.addView(searchView, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionPermanentlyDeniedDialog() {
        new AlertDialog.Builder(getActivity()).setTitle(C0134R.string.intercom_photo_access_denied).setMessage(C0134R.string.intercom_go_to_device_settings).setPositiveButton(C0134R.string.intercom_app_settings, new DialogInterface.OnClickListener() { // from class: com.intercom.input.gallery.GalleryInputFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            @SensorsDataInstrumented
            public void onClick(DialogInterface dialogInterface, int i) {
                GalleryInputFragment.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", GalleryInputFragment.this.getActivity().getPackageName(), null)));
                SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
            }
        }).setNegativeButton(C0134R.string.intercom_not_now, (DialogInterface.OnClickListener) null).show();
    }

    @VisibleForTesting
    void checkPermissionAndFetchImages(boolean z) {
        int permissionStatus = this.dataSource.getPermissionStatus();
        if (permissionStatus != 1) {
            if (permissionStatus == 2) {
                showEmptyOrPermissionScreen(permissionStatus);
                if (z) {
                    showPermissionPermanentlyDeniedDialog();
                    return;
                }
                return;
            }
            if (permissionStatus != 3) {
                fetchImagesIfNotFetched();
                return;
            }
        }
        showEmptyOrPermissionScreen(permissionStatus);
        if (z) {
            this.dataSource.requestPermission();
        }
    }

    @VisibleForTesting
    void fetchImagesIfNotFetched() {
        if (this.galleryImages.isEmpty()) {
            this.dataSource.getImages(0, null);
        }
    }

    protected abstract Injector getInjector(GalleryInputFragment galleryInputFragment);

    @VisibleForTesting
    @LayoutRes
    int getLayoutRes() {
        return this.expanded ? C0134R.layout.intercom_composer_fragment_composer_gallery_expanded : C0134R.layout.intercom_composer_fragment_composer_gallery;
    }

    @VisibleForTesting
    void launchLightBoxActivity(GalleryImage galleryImage) {
        FragmentActivity activity = getActivity();
        startActivityForResult(GalleryLightBoxActivity.createIntent(activity, galleryImage, getInjector(this).getLightBoxFragmentClass(this)), 14, ActivityOptionsCompat.makeCustomAnimation(activity, C0134R.anim.intercom_composer_slide_up, C0134R.anim.intercom_composer_stay).toBundle());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 14 || i2 != -1) {
            super.onActivityResult(i, i2, intent);
        } else if (this.galleryOutputListener != null) {
            this.galleryOutputListener.onGalleryOutputReceived((GalleryImage) intent.getParcelableExtra(GalleryInputFullScreenActivity.GALLERY_IMAGE));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GalleryOutputListener) {
            this.galleryOutputListener = (GalleryOutputListener) context;
        }
        if (context instanceof GalleryInputExpandedListener) {
            this.galleryInputExpandedListener = (GalleryInputExpandedListener) context;
        }
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.expanded = arguments.getBoolean(ARG_EXPANDED, false);
        }
        if (this.expanded) {
            this.layoutManager = new GridLayoutManager(getContext(), getResources().getInteger(C0134R.integer.intercom_composer_expanded_column_count));
        } else {
            this.layoutManager = new LinearLayoutManager(getContext(), 0, false);
        }
        this.injector = getInjector(this);
        this.dataSource = this.injector.getDataSource(this);
        this.dataSource.setListener(this.dataListener);
        this.imageLoader = this.injector.getImageLoader(this);
        this.endlessRecyclerScrollListener = new EndlessRecyclerScrollListener(this.layoutManager, this);
        this.recyclerAdapter = new GalleryRecyclerViewAdapter(LayoutInflater.from(getContext()), this.galleryImages, this.expanded, this, this.imageLoader);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutRes(), viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(C0134R.id.gallery_root_view);
        this.recyclerView = inflate.findViewById(C0134R.id.gallery_recycler_view);
        this.emptyLayout = (EmptyView) inflate.findViewById(C0134R.id.gallery_empty_view);
        this.contentLayout = (FrameLayout) inflate.findViewById(C0134R.id.gallery_content_layout);
        if (this.expanded) {
            setUpAppBar(this.injector, viewGroup2);
            this.recyclerView.addItemDecoration(new HeadingMarginDecoration(C0134R.dimen.intercom_composer_toolbar_height));
        } else {
            setUpPreviewViews(this.injector, viewGroup2);
        }
        this.emptyLayout.setActionButtonClickListener(new View.OnClickListener() { // from class: com.intercom.input.gallery.GalleryInputFragment.3
            /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:            if (r0 != 3) goto L11;     */
            @Override // android.view.View.OnClickListener
            @com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClick(android.view.View r4) {
                /*
                    r3 = this;
                    r0 = r3
                    com.intercom.input.gallery.GalleryInputFragment r0 = com.intercom.input.gallery.GalleryInputFragment.this
                    com.intercom.input.gallery.GalleryInputDataSource r0 = r0.dataSource
                    int r0 = r0.getPermissionStatus()
                    r5 = r0
                    r0 = r5
                    r1 = 1
                    if (r0 == r1) goto L29
                    r0 = r5
                    r1 = 2
                    if (r0 == r1) goto L1f
                    r0 = r5
                    r1 = 3
                    if (r0 == r1) goto L29
                    goto L35
                L1f:
                    r0 = r3
                    com.intercom.input.gallery.GalleryInputFragment r0 = com.intercom.input.gallery.GalleryInputFragment.this
                    com.intercom.input.gallery.GalleryInputFragment.access$000(r0)
                    goto L35
                L29:
                    r0 = r3
                    com.intercom.input.gallery.GalleryInputFragment r0 = com.intercom.input.gallery.GalleryInputFragment.this
                    com.intercom.input.gallery.GalleryInputDataSource r0 = r0.dataSource
                    r0.requestPermission()
                L35:
                    r0 = r4
                    com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper.trackViewOnClick(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.intercom.input.gallery.GalleryInputFragment.ViewOnClickListenerC01323.onClick(android.view.View):void");
            }
        });
        this.emptyLayout.setThemeColor(this.injector.getThemeColor(getContext()));
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.recyclerView.removeOnScrollListener(this.endlessRecyclerScrollListener);
        this.recyclerView.setLayoutManager((RecyclerView.LayoutManager) null);
    }

    @Override // com.intercom.input.gallery.adapter.OnImageClickListener
    public void onImageClicked(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition <= -1 || adapterPosition >= this.recyclerAdapter.getItemCount()) {
            return;
        }
        launchLightBoxActivity(this.recyclerAdapter.getItem(adapterPosition));
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputDeselected() {
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputReselected() {
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputSelected() {
        checkPermissionAndFetchImages(true);
    }

    @Override // com.intercom.input.gallery.adapter.EndlessScrollListener
    public void onLoadMore() {
        if (this.galleryImages.isEmpty() || this.dataSource.isLoading()) {
            return;
        }
        this.dataSource.getImages(this.galleryImages.size(), null);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        checkPermissionAndFetchImages(false);
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(this.recyclerAdapter);
        this.recyclerView.addOnScrollListener(this.endlessRecyclerScrollListener);
        if (this.expanded) {
            onInputSelected();
        }
        this.endlessRecyclerScrollListener.setMaxCount(this.dataSource.getCount());
    }

    @Override // com.intercom.composer.input.InputFragment
    protected void passDataOnViewCreated(@Nullable Bundle bundle) {
    }

    public void setGalleryExpandedListener(@Nullable GalleryInputExpandedListener galleryInputExpandedListener) {
        this.galleryInputExpandedListener = galleryInputExpandedListener;
    }

    public void setGalleryListener(@Nullable GalleryOutputListener galleryOutputListener) {
        this.galleryOutputListener = galleryOutputListener;
    }

    @VisibleForTesting
    void showEmptyOrPermissionScreen(int i) {
        if (i == 0) {
            if (!this.galleryImages.isEmpty()) {
                this.emptyLayout.setVisibility(8);
                this.contentLayout.setVisibility(0);
                return;
            }
            this.emptyLayout.setVisibility(0);
            this.emptyLayout.setActionButtonVisibility(8);
            this.emptyLayout.setTitle(this.injector.getEmptyViewTitle(getResources()));
            this.emptyLayout.setSubtitle(this.injector.getEmptyViewSubtitle(getResources()));
            this.contentLayout.setVisibility(8);
            return;
        }
        if (i == 1 || i == 2) {
            this.emptyLayout.setVisibility(0);
            this.emptyLayout.setTitle(C0134R.string.intercom_photo_access_denied);
            this.emptyLayout.setSubtitle(C0134R.string.intercom_allow_storage_access);
            this.emptyLayout.setActionButtonVisibility(0);
            this.contentLayout.setVisibility(8);
            return;
        }
        if (i != 3) {
            return;
        }
        this.emptyLayout.setVisibility(0);
        this.emptyLayout.setTitle(C0134R.string.intercom_access_photos);
        this.emptyLayout.setSubtitle(C0134R.string.intercom_storage_access_request);
        this.emptyLayout.setActionButtonVisibility(8);
        this.contentLayout.setVisibility(8);
    }

    @VisibleForTesting
    void showErrorScreen() {
        this.emptyLayout.setVisibility(0);
        this.emptyLayout.setActionButtonVisibility(8);
        this.emptyLayout.setTitle(this.injector.getErrorViewTitle(getResources()));
        this.emptyLayout.setSubtitle(this.injector.getErrorViewSubtitle(getResources()));
        this.contentLayout.setVisibility(8);
    }
}
