	$(document).ready(function() {
		
		var questionAnswerModal = $("#questionAnswerModal");
		
		

		function allChildQuestionsAnswered(){
			allRangesAnswered = true;
			$('input[type="number"]').each(function() {
				if($(this).val().toString() == "" || $(this).val().toString() == 'undefined'){
					allRangesAnswered = false;
					return false;
				}
			 });
			return allRangesAnswered;
		}
		
		function allMainQuestionAnswered(){
			var names = {};
			$('input:radio').each(function() { // find unique names
			      names[$(this).attr('name')] = true;
			});
			var count = 0;
			$.each(names, function() { // then count them
			      count++;
			});
			return ($('input:radio:checked').length == count);
		}
		
		function allQuestionsAnswered(){
			return allMainQuestionAnswered() && allChildQuestionsAnswered();
		}
		
		
		function showQuestionAnswerModalModal(){
			questionAnswerModal.modal('show');
			
			// if(allQuestionsAnswered()){
				// questionAnswerModal.modal('show');
			// }else{
				// alert("Please answer all the questions with the ranges!");
			// }
		}
		
		
		function saveAnswers() {
			var email = $("#email");
			var choices = {};
			$('input[type="radio"]:checked').each(function() {
				choices[$(this).attr('name')] = $(this).val();
			  });
			
			
			
			$('input[type="number"]').each(function() {
				choices[$(this).attr('name')] = $(this).val().toString();
			  });
			
			
			var user = {
					'email':email.val(),	
					'choices':choices
				};
			$.ajax({
				type : "POST",
				url : "/answers",
				data : JSON.stringify(user),
				contentType : "application/json",
				success : function(user) {
					$("input:radio").prop("checked", false),
					questionAnswerModal.modal('hide')
					
					noty({
			            text: "Your answers was successfully saved!",
			            layout: 'top',
			            type: 'success',
			            timeout: 5000
			        });
				},
				error(error) {
					noty({
			            text: "Application has encountered an error. Please contact your support",
			            layout: 'top',
			            type: 'error',
			            timeout: 5000
			        });
				}
			});
		}

		
		$('input[type="radio"]').on('change', function(e) {
		    questionId = e.currentTarget.name;
		    parentConditionValueWithChildQuestionId = $('#parent-condition-'+questionId);

		    if(parentConditionValueWithChildQuestionId != null && parentConditionValueWithChildQuestionId!= 'undefined'){
		    	if(parentConditionValueWithChildQuestionId.val() != null && parentConditionValueWithChildQuestionId.val() != 'undefined'){
		    		parentConditionValueWithChildQuestionIdSplitter = parentConditionValueWithChildQuestionId.val().split(',');
		    		parentConditionValue = parentConditionValueWithChildQuestionIdSplitter[0];
		    		childQuestionId = parentConditionValueWithChildQuestionIdSplitter[1];
		    	
		    		if(parentConditionValue == e.currentTarget.value){
		    			$('#question-'+childQuestionId).show();
		    		}else{
		    			$('#question-'+childQuestionId).hide();
		    		}
		    	}
		    }
		})
		
		$('#btnNext').on("click", showQuestionAnswerModalModal);
		$('#btnSave').on("click", saveAnswers);
		
	})