package com.oracle.oBootMyBatis03.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;
import com.oracle.oBootMyBatis03.model.Member1;
import com.oracle.oBootMyBatis03.service.EmpService;
import com.oracle.oBootMyBatis03.service.Paging;


@Controller
public class EmpController {

	@Autowired	
	private EmpService es;
	
	@Autowired
	private JavaMailSender  mailSender;
	
	
	
	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start..");
		int total = es.total();   // Emp Count -> 19
		System.out.println("EmpController total=>" + total);
		Paging  pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());   // 시작시 1
		emp.setEnd(pg.getEnd());       // 시작시 10 
        List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController listEmp.size()=>" + listEmp.size());
		model.addAttribute("listEmp",listEmp);
		model.addAttribute("pg",pg);
		model.addAttribute("totalCnt",total);		
		return "list";
	
	}
	@GetMapping(value="detail")
	public String detail(int empno, Model model) {

		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);

	return "detail";
	}
	
	@GetMapping(value="updateForm")
	public String updateForm(int empno,Model model) {
		Emp emp = es.detail(empno);
		System.out.println("EmpController updateForm emp.getHiredate() -->"+emp.getHiredate());
		model.addAttribute("emp",emp);
		return "updateForm";
	}
    
	@PostMapping(value="update")
	public String update(Emp emp, Model model) {
		//System.out.println("hiredate = "+emp.getHiredate() );
		int k = es.update(emp);
		System.out.println("es.update(emp) CNT -->"+k);
		model.addAttribute("kkk",k);   // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");   // Test Controller간 Data 전달
		return "redirect:list";	
		//return "forward:list";   //  Controller간 Data 전달시 활용(Model등에 담아서리....)
	}
	
	@GetMapping(value="writeForm")
	public String writeForm(Model model) {
		// emp 관리자
		List<Emp> list = es.listManager();
		System.out.println("EmpController writeForm list.size->"+list.size());
		model.addAttribute("empMngList",list);   // emp Manager List
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); // dept
	
		return "writeForm";
	}
	
	@PostMapping(value="write")
    public String write(Emp emp, Model model) {
		System.out.println("emp.getHiredate->"+emp.getHiredate());	
		int result = es.insert(emp);
		if (result > 0) return "redirect:list";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	
	}

	@RequestMapping(value="confirm")
	public String confirm(int empno, Model model) {
		System.out.println("EmpController confirm Start..");
	    Emp emp = es.detail(empno);
		model.addAttribute("empno", empno);
		if (emp !=null) {
			System.out.println("EmpController confirm 중복된 사번입니다");
			model.addAttribute("msg","중복된 사번입니다");			
			return "forward:writeForm";
		} else {
			System.out.println("EmpController confirm 사용 가능한 사번입니다");
			model.addAttribute("msg","사용 가능한 사번입니다");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value="delete")
	public String delete(int empno,Model model) {
		System.out.println("EmpController delete Start..");
		System.out.println("EmpController delete empno->"+empno);
		int k = es.delete(empno);
		return "redirect:list";
	}
	
	
	//  Procedure Test 입력화면 
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
	public String writeDeptIn(Model model) {
		 System.out.println("writeDeptIn Start..");
	    return "writeDept3";
	}

	
	@PostMapping(value="writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		es.insertDept(deptVO);   // Procedure Call 
		if (deptVO == null) {
			System.out.println("deptVO NULL");
		}else {
			System.out.println("RdeptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("RdeptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("RdeptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다 ^^");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	//  Procedure Cursor Test 입력화면 
	  @RequestMapping(value = "writeDeptCursor", method = RequestMethod.GET)
	  public String writeDeptCursor(Model model) {
		    System.out.println("EmpController writeDeptCursor Start...");
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("sDeptno", 10);
			map.put("eDeptno", 50);
		    System.out.println("EmpController writeDeptCursor selListDept Before");
			es.selListDept(map);
		    System.out.println("EmpController writeDeptCursor selListDept After");
			List<Dept> deptList = (List<Dept>) map.get("dept");
			System.out.println("deptList.dname[0].getDname->"+deptList.get(0).getDname());
			System.out.println("deptList.dname[0].getLoc->"+deptList.get(0).getLoc());
			System.out.println("deptList Size->"+ deptList.size());
			model.addAttribute("deptList", deptList);
		  
		  return "writeDeptCursor";
	  }
	
	    // Map ID -->TKlistEmpDept
		@RequestMapping(value="listEmpDept")
		public String listEmpDept(Model model) {
			EmpDept empDept = null;
			System.out.println("EmpController listEmpDept Start...");
			List<EmpDept> listEmpDept = es.listEmpDept();
			model.addAttribute("listEmpDept",listEmpDept);
			return "listEmpDept";
		}
	
	   @GetMapping(value="mailTransport")
		public String mailTransport(HttpServletRequest request, Model model) {
			System.out.println("mailSending...");
			String tomail = "ttaekwang3@naver.com";              // 받는 사람 이메일
			System.out.println(tomail);
			String setfrom = "ttaekwang3@gmail.com";
			String title = "mailTransport 입니다";                 // 제목
			try {
				// Mime 전자우편 Internet 표준 Format
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(tomail);       // 받는사람 이메일
				messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
				String tempPassword = (int) (Math.random() * 999999) + 1 + "";
				messageHelper.setText("임시 비밀번호입니다 : " + tempPassword); // 메일 내용
				System.out.println("임시 비밀번호입니다 : " + tempPassword);
				DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
			    messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
				mailSender.send(message);
				model.addAttribute("check", 1);   // 정상 전달
//				s.tempPw(u_id, tempPassword)  ;// db에 비밀번호를 임시비밀번호로 업데이트
			} catch (Exception e) {
				System.out.println(e);
				model.addAttribute("check", 2);  // 메일 전달 실패
			}
			return "mailResult";
		}

	
		 // Ajax  List Test
		 @RequestMapping(value="listEmpAjax")
		 public String listEmpAjax(Model model) {
				EmpDept empDept = null;
				System.out.println("Ajax  List Test Start");
				List<EmpDept> listEmp = es.listEmp(empDept);
				model.addAttribute("result","kkk");
				model.addAttribute("listEmp",listEmp);
				return "listEmpAjax";
		 }
	
		@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8")
		@ResponseBody
		public String getDeptName(int deptno, Model model) {
			   System.out.println("getDeptName deptno->"+deptno);
				return es.deptName(deptno);
		}
	
		 
		// Ajax  List Test
		@RequestMapping(value="listEmpAjax2")
		public String listEmpAjax2(Model model) {
				EmpDept empDept = null;
				System.out.println("Ajax  List Test Start");
				List<EmpDept> listEmp = es.listEmp(empDept);
				model.addAttribute("result","kkk");
				model.addAttribute("listEmp",listEmp);
				return "listEmpAjax2";
		}

		  // interCepter 시작 화면 
		  @RequestMapping(value = "interCeptorForm", method = RequestMethod.GET)
		  public String interCeptorForm(Model model) {
				  System.out.println("interCeptorForm Start");
			    return "interCeptorForm";
		  }

		  // interCepter 진행 Test  --> 2번째 수행 
		  @RequestMapping(value="interCeptor")
		  public String interCeptor(String id, Model model) {
				System.out.println("interCepter  Test Start");
				System.out.println("interCepter id->"+id);
				int memCnt = es.memCount(id);
				
				model.addAttribute("id",id);
				model.addAttribute("memCnt",memCnt);
				System.out.println("interCepter  Test End");
				
				return "interCeptor";   // User 존재하면  User 이용 조회 Page
	  }

	 // interCepter 진행 Test
	 @RequestMapping(value="doMemberList")
	 public String doMemberList(Model model, HttpServletRequest request){
			String ID =  (String) request.getSession().getAttribute("ID");
			System.out.println("doMemberList  Test Start  ID ->"+ID);
			Member1 member1 = null;
			// Member1 List Get Service
			List<Member1> listMem = es.listMem(member1);
			model.addAttribute("ID",ID);
			model.addAttribute("listMem",listMem);
			return "doMemberList";   // User 존재하면  User 이용 조회 Page
		 
	 }

	 // SampleInterceptor 내용을 받아 처리 
	 @RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	 public String doMemberWrite( Model model,  HttpServletRequest request) {
		   String ID =  (String) request.getSession().getAttribute("ID");
		   System.out.println("doMemberWrite....................");
		   model.addAttribute("id",ID);
	 
		 
	     return "doMemberWrite";
	 }
	
}
