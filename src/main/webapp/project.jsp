<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Project</title>
</head>
<body>
<form name="input" action="/ecrawler/file/uploadFile" method="post" enctype="multipart/form-data">
项目名称: 
<input type="text" name="projectName" />
<br />
项目描述: 
<input type="text" name="projectDescription" />
<br />

开始时间: 
<input type="text" name="startTime" />
<br />

结束时间: 
<input type="text" name="endedTime" />
<br />

最大执行时间: 
<input type="text" name="maxExecutingTime" />
<br />

执行周期: 
<input type="text" name="ExecutePeriod" />
<br />

任务文件: 
<input type="file" name="taskFile" />
<br />
<input type="submit" value="Submit" />


</form>
</body>
</html>