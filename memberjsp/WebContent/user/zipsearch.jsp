<%@ page language="java" contentType="text/html; charset=UTF-8" %>


<div id="zipModal" class="modal fade" role="dialog">
	<h5 class="modal-title" id="myModalLabel">우편번호검색</h5>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>    
            <div class="modal-body text-center">
            	<form id = "zip_codeForm">
            		<div align="center">
            			<label>도로명 주소검색</label>
            		</div>
					<div class="input-group" align="left">
						<input type="text" class="form-control" id="doro" name="doro" placeholder="검색 할 도로명 입력(예: 구로디지털로, 여수울로)">
						<span class="input-group-btn">
						<input type="submit" class="btn btn-warning" value="검색" id="searchBtn">
						</span>
					</div>
                </form>
                <div style="width:100%; height:200px; overflow:auto">
                	<table class = "table text-center">
                		<thead>
                		<tr>
                			<th style="width:150px;">우편번호</th>
                			<th style="width:600px;">주소</th>
                		</tr>
                		</thead>
                		<tbody id="zip_codeList"></tbody>
                	</table>
                </div>
            </div>
        </div>
    </div>
</div>