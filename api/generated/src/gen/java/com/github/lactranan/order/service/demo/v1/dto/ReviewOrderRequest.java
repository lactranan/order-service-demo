package com.github.lactranan.order.service.demo.v1.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonTypeName;



@JsonTypeName("reviewOrder_request")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen")
public class ReviewOrderRequest  implements Serializable {
  public enum DecisionEnum {

    APPROVE(String.valueOf("APPROVE")), REJECT(String.valueOf("REJECT"));


    private String value;

    DecisionEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Convert a String into String, as specified in the
     * <a href="https://download.oracle.com/otndocs/jcp/jaxrs-2_0-fr-eval-spec/index.html">See JAX RS 2.0 Specification, section 3.2, p. 12</a>
     */
    public static DecisionEnum fromString(String s) {
        for (DecisionEnum b : DecisionEnum.values()) {
            // using Objects.toString() to be safe if value type non-object type
            // because types like 'int' etc. will be auto-boxed
            if (java.util.Objects.toString(b.value).equals(s)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected string value '" + s + "'");
    }

    @JsonCreator
    public static DecisionEnum fromValue(String value) {
        for (DecisionEnum b : DecisionEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid DecisionEnum decision;
  private @Valid String reason;
  private @Valid String approvalCode;

  /**
   * Review decision
   **/
  public ReviewOrderRequest decision(DecisionEnum decision) {
    this.decision = decision;
    return this;
  }

  
  @JsonProperty("decision")
  public DecisionEnum getDecision() {
    return decision;
  }

  @JsonProperty("decision")
  public void setDecision(DecisionEnum decision) {
    this.decision = decision;
  }

  /**
   * Reason for the decision
   **/
  public ReviewOrderRequest reason(String reason) {
    this.reason = reason;
    return this;
  }

  
  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }

  @JsonProperty("reason")
  public void setReason(String reason) {
    this.reason = reason;
  }

  /**
   * Admin approval code stored in DB
   **/
  public ReviewOrderRequest approvalCode(String approvalCode) {
    this.approvalCode = approvalCode;
    return this;
  }

  
  @JsonProperty("approvalCode")
  public String getApprovalCode() {
    return approvalCode;
  }

  @JsonProperty("approvalCode")
  public void setApprovalCode(String approvalCode) {
    this.approvalCode = approvalCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewOrderRequest reviewOrderRequest = (ReviewOrderRequest) o;
    return Objects.equals(this.decision, reviewOrderRequest.decision) &&
        Objects.equals(this.reason, reviewOrderRequest.reason) &&
        Objects.equals(this.approvalCode, reviewOrderRequest.approvalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(decision, reason, approvalCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewOrderRequest {\n");
    
    sb.append("    decision: ").append(toIndentedString(decision)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    approvalCode: ").append(toIndentedString(approvalCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

