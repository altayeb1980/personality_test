	$(document).ready(function() {
		
		var questionAnswerModal = $("#questionAnswerModal");
		
		
		
		
		function allQuestionsAnswered(){
			var allQuestionsAnswered = true;
			var radioButtonNames = {};
			$("div.parent-questions-div ").each(function (){
				if($(this).attr("style") == 'display:show'){
						
					$(this).find('input:radio').each(function() { // find unique names
						radioButtonNames[$(this).attr("name")] = true;
					});
					
					$(this).find('div.child-questions-div').each(function (){
						if($(this).attr("style") == 'display:show'){
							var numberFieldRef = $(this).find('input[type="number"]');
							if(numberFieldRef.val() == "" || numberFieldRef.val() == "undefined"){
								allQuestionsAnswered = false;
								return false;
							}
						}
					});
				}
			});
			
			var count = 0;
			$.each(radioButtonNames, function() { // then count them
			      count++;
			});
			
			if(count> 0){
				return allQuestionsAnswered && ($('input:radio:checked').length == count);
			}else{
				return allQuestionsAnswered;
			}
		
		}
		
		
		function showQuestionAnswerModalModal(){
			if(allQuestionsAnswered()){
				questionAnswerModal.modal('show');
			}else{
				alert("Please answer all the questions with the ranges!");	
			}
		}
		
		function validateEmail(email) {
			var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			if(email == "" || email =="undefined") return false;
			  return emailReg.test(email);
			}
		
		function saveAnswers() {
			var email = $("#email").val();
			if(!validateEmail(email)){
				alert("Please enter valid email");
				return;
			}
			
			var choices = {};
			
			$("div.parent-questions-div ").each(function (){
				if($(this).attr("style") == 'display:show'){
					var radioRef = $(this).find('input:radio:checked');
					radioRef.each(function() { // find unique names
						choices[radioRef.attr("name")] = radioRef.val();
					});
					
					$(this).find('div.child-questions-div').each(function (){
						if($(this).attr("style") == 'display:show'){
							var numberRef = $(this).find('input[type="number"]');
							choices[numberRef.attr('name')] = numberRef.val().toString();
						}
					});
				}
			});

			var user = {
					'email':email,	
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
					questionAnswerModal.modal('hide')

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
		    parentConditionQuestionValue = $('#parent-condition-'+questionId).val();

		    if(parentConditionQuestionValue != null && parentConditionQuestionValue!= 'undefined'){
		    		if(parentConditionQuestionValue == e.currentTarget.value){
		    			$('div.child-questions-div').attr("style","display:show");
		    		}else{
		    			$('div.child-questions-div').attr("style","display:none");
		    		}
		    }
		});
		
		$('#btnNext').on("click", showQuestionAnswerModalModal);
		$('#btnSave').on("click", saveAnswers);
		
	})