package com.joblessfriend.jobfinder.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joblessfriend.jobfinder.admin.service.AdminCommunityCommentService;
import com.joblessfriend.jobfinder.community.domain.PostCommentVo;
import com.joblessfriend.jobfinder.util.Pagination;
import com.joblessfriend.jobfinder.util.SearchVo;

@RequestMapping("/admin/community/comment") 
@Controller
public class AdminCommunityCommentController {
	
	private Logger logger = LoggerFactory.getLogger(AdminCommunityPostController.class); // 로그 출력용
	private final String logTitleMsg = "==Admin / comment control==";
	
	@Autowired
	private AdminCommunityCommentService commentService;
	
	@GetMapping("")
	public String communityCommentSelectList(Model model, @RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "") String keyword) {
		
		/* 페이지네이션 */
		SearchVo searchVo = new SearchVo();
		searchVo.setKeyword(keyword);
		searchVo.setPage(page);
		searchVo.setRecordSize(8);	
		
	    int totalCount = commentService.getCommentTotalCount(searchVo);//전체 데이터 수
	    Pagination pagination = new Pagination(totalCount, searchVo);
	    
        searchVo.setStartRow(pagination.getLimitStart() + 1); // 1부터 시작
        searchVo.setEndRow(searchVo.getStartRow() + searchVo.getRecordSize() - 1);
        /* 페이지네이션 세팅 끝 */
        
     // 커뮤니티 리스트 전체 조회
	    List<PostCommentVo> commentList = commentService.commentSelectList(searchVo);  // DB에서 리스트 가져오기
	 // 변환된 커뮤니티 리스트를 모델에 추가(화면에 출력하기 위함)
	    model.addAttribute("commentList", commentList);
	    model.addAttribute("searchVo", searchVo);
	    model.addAttribute("pagination", pagination);
        
		return "admin/community/commentView";
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> communityCommentDelete(@RequestBody List<Integer> commentIdList) {
		logger.info("게시물 삭제 메서드");

		for (Integer i : commentIdList) {
			System.out.println("삭제할 댓글 Id " + i);
		}		
		commentService.commentDelete(commentIdList);

		
		return ResponseEntity.ok("삭제완료"); 
	}
	
	

}
