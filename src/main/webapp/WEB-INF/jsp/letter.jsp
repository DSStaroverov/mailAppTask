<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/mailApp.common.js" defer></script>
<script type="text/javascript" src="resources/js/mailApp.letter.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Folders</h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <button class="btn btn-primary" onclick="window.location = 'folder'">
            <span class="fa fa-backward"></span>
            Back to folders
        </button>
        <button class="btn btn-primary" onclick="newLetter()">
            <span class="fa fa-envelope"></span>
            Send letter
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>From</th>
                <th>To</th>
                <th>Title</th>
                <th>Time</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="input-group mb-2">
                        <label for="sender" ><spring:message code="letter.sender"/> </label>
                        <input type="text" class="form-control form-control-sm" id="sender" name="sender">
                    </div>

                    <div class="input-group mb-2">
                        <label for="recipient" class="col-form-label"><spring:message code="letter.recipient"/> </label>
                        <input type="text" class="form-control  form-control-sm" id="recipient" name="recipient">
                    </div>

                    <div class="input-group mb-2">
                        <label for="title" class="col-form-label"><spring:message code="letter.title"/> </label>
                        <input type="text" class="form-control form-control-sm" id="title" name="title">
                    </div>

                    <div class="input-group mb-2">
                        <label for="sendTime" class="col-form-label"><spring:message code="letter.sendTime"/> </label>
                        <input class="form-control form-control-sm" id="sendTime" name="sendTime">
                    </div>

                    <div class="form-group">
                        <label for="message" class="col-form-label"><spring:message code="letter.message"/></label>
                        <textarea class="form-control" rows="15" id="message" name="message"></textarea>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
