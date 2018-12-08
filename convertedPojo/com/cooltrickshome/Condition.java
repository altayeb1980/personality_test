
package com.cooltrickshome;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "predicate",
    "if_positive"
})
public class Condition {

    @JsonProperty("predicate")
    private Predicate predicate;
    @JsonProperty("if_positive")
    private IfPositive ifPositive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("predicate")
    public Predicate getPredicate() {
        return predicate;
    }

    @JsonProperty("predicate")
    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public Condition withPredicate(Predicate predicate) {
        this.predicate = predicate;
        return this;
    }

    @JsonProperty("if_positive")
    public IfPositive getIfPositive() {
        return ifPositive;
    }

    @JsonProperty("if_positive")
    public void setIfPositive(IfPositive ifPositive) {
        this.ifPositive = ifPositive;
    }

    public Condition withIfPositive(IfPositive ifPositive) {
        this.ifPositive = ifPositive;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Condition withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(predicate).append(ifPositive).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Condition) == false) {
            return false;
        }
        Condition rhs = ((Condition) other);
        return new EqualsBuilder().append(predicate, rhs.predicate).append(ifPositive, rhs.ifPositive).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
