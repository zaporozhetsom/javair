package domain.entities;

import domain.Entity;

/**
 * Created by zom on 12.10.2017.
 */
public class Dummy implements Entity {
    private Long id;
    private Long thirdPartyId;
    private String name;

    public static class Builder {
        private Long id;
        private Long thirdPartyId;
        private String name;


        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder thirdPartyId(Long value) {
            this.thirdPartyId = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Dummy build() {
            return new Dummy(this);
        }
    }

    private Dummy(Builder builder) {
        this.id = builder.id;
        this.thirdPartyId = builder.thirdPartyId;
        this.name = builder.name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getThirdPartyId() {
        return thirdPartyId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dummy dummy = (Dummy) o;

        if (!id.equals(dummy.id)) return false;
        return name.equals(dummy.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
