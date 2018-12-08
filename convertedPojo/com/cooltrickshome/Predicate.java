
package com.cooltrickshome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "exactEquals"
})
public class Predicate {

    @JsonProperty("exactEquals")
    private List<String> exactEquals = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("exactEquals")
    public List<String> getExactEquals() {
        return exactEquals;
    }

    @JsonProperty("exactEquals")
    public void setExactEquals(List<String> exactEquals) {
        this.exactEquals = exactEquals;
    }

    public Predicate withExactEquals(List<String> exactEquals) {
        this.exactEquals = exactEquals;
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

    public Predicate withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(exactEquals).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Predicate) == false) {
            return false;
        }
        Predicate rhs = ((Predicate) other);
        return new EqualsBuilder().append(exactEquals, rhs.exactEquals).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
