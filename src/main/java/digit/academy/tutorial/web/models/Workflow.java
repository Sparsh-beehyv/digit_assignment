//package digit.academy.tutorial.web.models;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.validation.annotation.Validated;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Data;
//import lombok.Builder;
//
///**
// * Workflow
// */
//@Validated
//@jakarta.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2025-07-17T16:18:28.406185974+05:30[Asia/Kolkata]")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Workflow   {
//        @JsonProperty("action")
//          @NotNull
//
//                private String action = null;
//
//        @JsonProperty("comment")
//
//                private String comment = null;
//
//        @JsonProperty("assignees")
//
//                private List<String> assignees = null;
//
//
//        public Workflow addAssigneesItem(String assigneesItem) {
//            if (this.assignees == null) {
//                this.assignees = new ArrayList<>();
//            }
//            this.assignees.add(assigneesItem);
//            return this;
//        }
//
//}
