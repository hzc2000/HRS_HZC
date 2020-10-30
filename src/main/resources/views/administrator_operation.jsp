<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<div class="row">
    <div class="col-3">
        <a class="btn-success"  href="${pageContext.request.contextPath}/download/downloadHouseRentMessage">下载房屋租用相关信息</a>
        <a class="btn-success"  href="${pageContext.request.contextPath}/deposit">押金管理</a>
    </div>
    <div class="col-6">
        <form action="${pageContext.request.contextPath}/upload/importLandlordFromExcel" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file1">批量导入房东信息(请上传excel文件)：</label>
                <input type="file"
                       class="form-control" name="file" id="file1" aria-describedby="helpId" placeholder="">
            </div>
            <button type="submit" class="btn btn-primary">上传</button>
        </form>
        <form action="${pageContext.request.contextPath}/upload/importHouseFromExcel" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file2">批量导入房屋信息(请上传excel文件)：</label>
                <input type="file"
                       class="form-control" name="file" id="file2" aria-describedby="helpId" placeholder="">
            </div>
            <button type="submit" class="btn btn-primary">上传</button>
        </form>
        <form action="${pageContext.request.contextPath}/upload/importCommunityFromExcel" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file3">批量导入小区信息(请上传excel文件)：</label>
                <input type="file"
                       class="form-control" name="file" id="file3" aria-describedby="helpId" placeholder="">
            </div>
            <button type="submit" class="btn btn-primary">上传</button>
        </form>
    </div>

</div>
<%@ include file="common/footer.jsp"%>