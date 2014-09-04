package com.mifos.objects.system;

/**
 * Created by antoniocarella on 6/19/14.
 */

import java.io.Serializable;

/**
 * Immutable data object represent code-value data in system.
 */
public class CodeValueData implements Serializable {

    private final Long id;

    private final String name;

    private final Integer position;

    public static CodeValueData instance(final Long id, final String name, final Integer position) {
        return new CodeValueData(id, name, position);
    }

    public static CodeValueData instance(final Long id, final String name) {
        return new CodeValueData(id, name, null);
    }

    private CodeValueData(final Long id, final String name, final Integer position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

}

