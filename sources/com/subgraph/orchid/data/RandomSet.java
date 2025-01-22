package com.subgraph.orchid.data;

import com.subgraph.orchid.TorException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/RandomSet.class */
public class RandomSet<E> {
    private final Set<E> set = new HashSet();
    private final List<E> list = new ArrayList();
    private final SecureRandom random = createRandom();

    private static SecureRandom createRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new TorException(e);
        }
    }

    public boolean add(E e) {
        if (!this.set.add(e)) {
            return false;
        }
        this.list.add(e);
        return true;
    }

    public void clear() {
        this.set.clear();
        this.list.clear();
    }

    public boolean contains(Object obj) {
        return this.set.contains(obj);
    }

    public E getRandomElement() {
        return this.list.get(this.random.nextInt(this.list.size()));
    }

    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    public boolean remove(Object obj) {
        if (!this.set.remove(obj)) {
            return false;
        }
        this.list.remove(obj);
        return true;
    }

    public int size() {
        return this.set.size();
    }
}
