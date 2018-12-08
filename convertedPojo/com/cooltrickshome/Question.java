
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
    "question",
    "category",
    "question_type"
})
public class Question {

    @JsonProperty("question")
    private String question;
    @JsonProperty("category")
    private String category;
    @JsonProperty("question_type")
    private QuestionType questionType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }

    public Question withQuestion(String question) {
        this.question = question;
        return this;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    public Question withCategory(String category) {
        this.category = category;
        return this;
    }

    @JsonProperty("question_type")
    public QuestionType getQuestionType() {
        return questionType;
    }

    @JsonProperty("question_type")
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Question withQuestionType(QuestionType questionType) {
        this.questionType = questionType;
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

    public Question withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(question).append(category).append(questionType).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Question) == false) {
            return false;
        }
        Question rhs = ((Question) other);
        return new EqualsBuilder().append(question, rhs.question).append(category, rhs.category).append(questionType, rhs.questionType).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
