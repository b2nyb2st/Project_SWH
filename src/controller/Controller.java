package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;


@WebServlet("*.slime")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Command command = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		switch(com) {
		//매장 관리페이지
		case "/managerPage.slime":
			new ManagerPageCommand().execute(request, response);
			viewPage = "managerPage.jsp";
			break;
		//메인 상점 검색
		case "/mainStore.slime":
			viewPage = "mainStore.jsp";
			break;
		//상점 선택 결과
		case "/storeView.slime":
			new StoreViewCommand().execute(request, response);
			viewPage = "storeView.jsp";
			break;
		//메인 제품 검색
		case "/mainProduct.slime":
			viewPage = "mainProduct.jsp";
			break;
		//제품 선택 결과
		case "/productView.slime":
			new StoreViewCommand().execute(request, response);
			viewPage = "productView.jsp";
			break;		
		//매장 정보 변경
		case "/storeSetting.slime":
			new ManagerPageCommand().execute(request, response); //매장관리페이지와 동일한 데이터를 가져오기때문에 Command재사용
			viewPage = "storeSetting.jsp";
			break;
		//매자 정보 변경 확인
		case "/storeSettingOk.slime":
			new StoreSettingOkCommand().execute(request, response);
			viewPage = "storeSettingOk.jsp";
			break;
		//제품 관리
		case "/productList.slime":
			new StoreViewCommand().execute(request, response);
			viewPage = "productList.jsp";
			break;
		//제품 등록
		case "/addProduct.slime":
			//TODO
			viewPage = "addProduct.jsp";
			break;
		//제품 정보 수정
		case "/productSetting.slime":
			new ProductSettingCommand().execute(request, response);
			viewPage = "productSetting.jsp";
			break;
		case "/productSettingOk.slime":
			new ProductSettingOkCommand().execute(request, response);
			viewPage = "productSettingOk.jsp";
			break;
		//이벤트 목록
		case "/eventList.slime":
			new EventListCommand().execute(request, response);
			viewPage = "eventList.jsp";
			break;
		//이벤트 작성
		case "/eventWrite.slime":
			viewPage = "eventWrite.jsp";
			break;
		//이벤트 작성 확인
		case "/eventWriteOk.slime":
			new EventWriteCommand().execute(request, response);
			viewPage = "eventWriteOk.jsp";
			break;
		//로그인 페이지
		case "/login.slime":
			viewPage = "login.jsp";
			break;
		// 로그인 OK
		case "/loginOk.slime":
			new LoginOkCommand().execute(request, response);
			viewPage = "loginOk.jsp";
			break;
		}
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
