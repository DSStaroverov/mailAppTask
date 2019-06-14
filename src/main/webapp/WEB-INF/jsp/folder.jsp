<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/mailApp.common.js" defer></script>
<script type="text/javascript" src="resources/js/mailApp.folder.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Folders</h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <button class="btn btn-primary" onclick="window.location = 'email'">
            <span class="fa fa-backward"></span>
            Back to E-mails
        </button>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Add Folder
        </button>
        <button class="btn btn-primary" onclick="newLetter()">
            <span class="fa fa-envelope"></span>
            Send letter
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Name</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle">add</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="folder.name"/></label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    save
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
