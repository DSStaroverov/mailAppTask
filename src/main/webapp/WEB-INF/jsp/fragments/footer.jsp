<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--https://getbootstrap.com/docs/4.0/examples/sticky-footer/--%>

<div class="modal fade" tabindex="-1" id="newLetterRow">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="newLetterTitle">add</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="newLetterForm">

                    <input type="hidden" id="idNewLetter" name="id">

                    <div class="input-group mb-2">
                        <label for="recipientLetter" class="col-form-label"><spring:message code="letter.recipient"/></label>
                        <input type="text" class="form-control" id="recipientLetter" name="recipient">
                    </div>

                    <div class="input-group mb-2">
                        <label for="titleLetter" class="col-form-label" ><spring:message code="letter.title"/></label>
                        <input type="text" class="form-control" id="titleLetter" name="title">
                    </div>

                    <div class="form-group">
                        <label for="messageLetter" class="col-form-label"><spring:message code="letter.message"/></label>
                        <textarea class="form-control" rows="15" id="messageLetter" name="message"></textarea>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="sendLetter()">
                    <span class="fa fa-check"></span>
                    Send
                </button>
            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <span class="text-muted"></span>
    </div>
</footer>