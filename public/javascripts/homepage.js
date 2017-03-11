$(document).ready(function(){
         $('#singinbtn').on("click",function(){
         jsRoutes.controllers.SignInController.SignIn().ajax({
                      success: function(data){
                        $('#body').html(data);
                      },
                      error: function(){
                      $('#body').html("hello");
                      alert("fail")
                    }
          })
      })

      $('#singupbtn').on("click",function(){
                jsRoutes.controllers.SignUpController.SignUp().ajax({
                            success: function(data){
                              $('#body').html(data);
                            },
                            error: function(){
                            $('#body').html("hello");
                            alert("fail")
                          }
                })
            })
});