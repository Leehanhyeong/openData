package com.increpas.data;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import kr.go.vo.DataVO;

@Controller
public class IndexController {
	
	
	
	@RequestMapping("/")
	public String index(Model model) throws Exception { //모델앤뷰도 상관없다
		//xml이니 JDOM을 사용한다.
		//자바에서는 웹상의경로를 연결할 때 URL객체생성하여 url을 집어넣어준다.
		//스트링 버퍼를 써서 편집해야 한다. 
		StringBuffer sb = new StringBuffer(
				"http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?");
				
		
		sb.append("serviceKey=VXGxM3FjoOVHGwjcIqHM4pa9aZW7Gbp0AvSTDrBcXpvwbv7QRBo6lrB9sPt9EqoldaidCOHjn%2F1E9XwDQm0%2BOA%3D%3D");
		sb.append("&MobileOS=ETC");
		sb.append("&MobileApp=AppTest");
		sb.append("&arrange=A");
		sb.append("&listYN=Y");
		sb.append("&eventStartDate=20211101");
		sb.append("&pageNo=1");
		sb.append("&numOfRows=10"); //이걸 붙혀야한다.
		
		
		URL url = new URL(sb.toString());//웹상의 경로를 객체화 시킨것
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();//http이기때문에
		
		//응답 받을 데이터의 형식을 지정
		conn.setRequestProperty("Content-Type", "application/xml");
		
		//연결 - 요청!
		conn.connect();
		
		//JDOM활용으로 연결된 URL로부터 응답메세지를 받아 xml문서와 시킨다.
		SAXBuilder builder = new SAXBuilder(); //로더  default생성자.
		Document document = builder.build(conn.getInputStream());//JDOM이 대단한 놈이다.
		
		//Document를 얻었으니 이제 Root엘리먼트만 얻으면 된다.
		//DOcument를 얻어내야 안에있는 Root엘리먼트를 얻어야한다. sqlsessionFactory처럼
		Element root = document.getRootElement();
		
		//루트엘리머트 안에는 header와 body가 있다. 여기서 우린 body를 얻어내자.
		//지금은 1개이기때문에 
		Element body = root.getChild("body");
		
		//body안에 있는 items라는 엘리먼트를 얻어내자
		//얘도 1개이기때문에
		Element items = body.getChild("items");
		
		//items요소 안에 item요소들을 얻어내자!
		List<Element> item_list = items.getChildren("item");
		//System.out.println(item_list.size());
		
		//반복문을 돌면서 item요소들을 DataVO로 만들어 준다.
		//그리고 배열에 저장!
		DataVO[] ar = new DataVO[item_list.size()];//공간만 만들어진것
		int i = 0;
			for(Element item : item_list) {
				//하나의 item요소에서원하는값 얻어내자(title adr1 ...)
				String title= item.getChildText("title"); //제목2
				String eventstartdate= item.getChildText("eventstartdate");
				String eventenddate= item.getChildText("eventenddate");
				String firstimage= item.getChildText("firstimage");
				String firstimage2= item.getChildText("firstimage2");//썸네일1
				String mapx= item.getChildText("mapx");
				String mapy= item.getChildText("mapy");
				String addr1= item.getChildText("addr1");//주소3
				String addr2= item.getChildText("addr2");
				String tel= item.getChildText("tel");//전화번호4
				
				//vo객체로 생성한다.
			DataVO vo = new DataVO(title, addr1, addr2, tel, firstimage, firstimage2, eventstartdate, eventenddate, mapx, mapy);
			
			//생성된 vo가 사라지기 전에 배열에 저장하자!
				ar[i++] = vo;
			}
			//이동되는 index.jsp에서 표현하기 위해
			//model에 저장
			model.addAttribute("list", ar);
			
		
			
		
		
		return "index";
	}
}
