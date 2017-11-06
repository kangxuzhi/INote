package org.com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.entitys.NoteUser;

public class GetNoteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String phone = request.getParameter("phone");
		String mess = request.getParameter("mess");
		//System.out.println(phone + "--" + mess);

		/*
		 * if(NoteUser.getRandnum().equals(mess)){ request.setAttribute("msg",
		 * "验证成功"); }else{ request.setAttribute("msg", "验证码错误");
		 * request.getRequestDispatcher("").forward(request, response); }
		 */
		// 禁止浏览缓存随机图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 通知客户机以图片方式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		// 在内存中创建一张图片
		BufferedImage image = new BufferedImage(80, 30,
				BufferedImage.TYPE_INT_RGB);
		// 向图片上写数据
		Graphics g = image.getGraphics();
		// 设置背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 80, 30);
		// 设置写入数据的颜色和字体
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		// 向图片上写数据
		String num = makeNum();
		// 将生成的数保存到sess
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);
		// 把写好的数据的图片输出给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());

	}

	/**
	 * 生成随机数
	 */
	public String makeNum() {
		Random r = new Random();
		String num = r.nextInt(99999) + "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 5 - num.length(); i++) {
			buffer.append("0");
		}
		num = buffer.toString() + num;
		return num;
	}

}
