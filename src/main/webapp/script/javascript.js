
$(document).ready(function(){
    $('#1').click(function(){
        $('#content').load("addOptionForm.jsp");
    });
    $('#2').click(function(){
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'showOptions'},
            success: function(){
                $('#content').load("addTariffForm.jsp");
            }
        });
    });
    $('#3').click(function(){
        $('content').load();
    });
    $('#4').click(function(){
        $('#content').load("addClientForm.jsp");
    });
    $('#5').click(function(){
        $('content').load();
    });
    $('#6').click(function(){
        $('content').load();
    });
    $('#7').click(function(){
        $('content').load();
    });
    $('#8').click(function(){
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'showOptions'},
            success: function(){
                $('#content').load("addIncOptionForm.jsp");
            }
        });
    });
    $('#9').click(function(){
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'showOptions'},
            success: function(){
                $('#content').load("addReqOptionForm.jsp");
            }
        });
    });
});
