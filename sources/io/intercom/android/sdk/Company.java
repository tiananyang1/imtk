package io.intercom.android.sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.twig.Twig;
import io.intercom.android.sdk.utilities.CustomAttributeValidator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Company.class */
public class Company {
    private static final String COMPANY_ID = "id";
    private static final String CREATED_AT = "created_at";
    private static final String CUSTOM_ATTRIBUTES = "custom_attributes";
    private static final String MONTHLY_SPEND = "monthly_spend";
    private static final String NAME = "name";
    private static final String PLAN = "plan";
    private static final Twig TWIG = LumberMill.getLogger();
    private final Map<String, Object> attributes;
    private final Map<String, Object> customAttributes;

    /* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/Company$Builder.class */
    public static final class Builder {
        final Map<String, Object> attributes = new HashMap();
        final Map<String, Object> customAttributes = new HashMap();

        public Company build() {
            return new Company(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Builder builder = (Builder) obj;
            if (this.attributes.equals(builder.attributes)) {
                return this.customAttributes.equals(builder.customAttributes);
            }
            return false;
        }

        public int hashCode() {
            return (this.attributes.hashCode() * 31) + this.customAttributes.hashCode();
        }

        public String toString() {
            return "Builder{attributes=" + this.attributes + ", customAttributes=" + this.customAttributes + '}';
        }

        public Builder withCompanyId(@Nullable String str) {
            this.attributes.put(Company.COMPANY_ID, str);
            return this;
        }

        public Builder withCreatedAt(@Nullable Long l) {
            this.attributes.put(Company.CREATED_AT, l);
            return this;
        }

        public Builder withCustomAttribute(@NonNull String str, @Nullable Object obj) {
            if (str == null) {
                Company.TWIG.w("The key you provided was null for the attribute " + obj, new Object[0]);
                return this;
            }
            if (CustomAttributeValidator.isValid(obj)) {
                this.customAttributes.put(str, obj);
                return this;
            }
            Company.TWIG.w("The custom company attribute " + str + " was of type " + obj.getClass().getSimpleName() + " We only accept the following types: " + CustomAttributeValidator.getAcceptedTypes(), new Object[0]);
            return this;
        }

        public Builder withCustomAttributes(@NonNull Map<String, ?> map) {
            if (map == null) {
                Company.TWIG.w("The map of attributes you provided was null.", new Object[0]);
                return this;
            }
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (CustomAttributeValidator.isValid(entry.getValue())) {
                    this.customAttributes.put(entry.getKey(), entry.getValue());
                } else {
                    Company.TWIG.w("The custom company attribute " + entry.getKey() + " was of type " + entry.getClass().getSimpleName() + " We only accept the following types: " + CustomAttributeValidator.getAcceptedTypes(), new Object[0]);
                }
            }
            return this;
        }

        public Builder withMonthlySpend(@Nullable Integer num) {
            this.attributes.put(Company.MONTHLY_SPEND, num);
            return this;
        }

        public Builder withName(@Nullable String str) {
            this.attributes.put(Company.NAME, str);
            return this;
        }

        public Builder withPlan(@Nullable String str) {
            this.attributes.put(Company.PLAN, str);
            return this;
        }
    }

    Company(Builder builder) {
        this.attributes = builder.attributes;
        this.customAttributes = builder.customAttributes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Company company = (Company) obj;
        if (this.attributes.equals(company.attributes)) {
            return this.customAttributes.equals(company.customAttributes);
        }
        return false;
    }

    Map<String, Object> getAttributes() {
        if (!this.customAttributes.isEmpty()) {
            this.attributes.put(CUSTOM_ATTRIBUTES, this.customAttributes);
        }
        return this.attributes;
    }

    public int hashCode() {
        return (this.attributes.hashCode() * 31) + this.customAttributes.hashCode();
    }

    public String toString() {
        return "Company{attributes=" + this.attributes + ", customAttributes=" + this.customAttributes + '}';
    }
}
