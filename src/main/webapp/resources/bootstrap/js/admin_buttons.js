
$(document).ready(function(){
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
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'getTariffs'},
            success: function(){
                $('#content').load("addContractForm.jsp");
            }
        });
    });
    $('#5').click(function(){
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'getClients'},
            success: function(){
                $('#content').load("showClients.jsp");
            }
        });
    });
    $('#6').click(function(){
        $.ajax('adminServlet', {
            type:'post',
            data: {'action':'getContracts'},
            success: function(){
                $('#content').load('showContracts.jsp');
            }
        });
    });
    $('#7').click(function(){
        $('#content').load('addRoleForm.jsp');
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
    $('#10').click(function(){
        $.ajax('adminServlet', {
            type: 'post',
            data: {'action':'showOptions'},
            success: function(){
                $('#content').load("showOptions.jsp");
            }
        });
    });
});
