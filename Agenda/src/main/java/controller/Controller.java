package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;


// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns= {"/Controller", "/main","/lista","/insert","/select","/update","/delete","/report"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
       /** The dao. */
       DAO dao = new DAO();
       
       /** The contato. */
       JavaBeans contato = new JavaBeans();
    
    /**
     * Instantiates a new controller.
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Teste de conexão.
		//dao.testeConexao();
		String requisicao = request.getServletPath();
		System.out.println(requisicao);
		
		if(requisicao.equals("/main")) {
			contatos(request,response);
			
		}
		else if(requisicao.equals("/insert")){
			novoContato(request,response);
		}
		
		
		else if(requisicao.equals("/lista")) {
			ListadeContatos(request,response);
			
		}
		else if(requisicao.equals("/select")) {
			selecionarContato(request,response);
			
		}
		else if(requisicao.equals("/update")) {
			editarContato(request,response);
		}
		else if(requisicao.equals("/delete")) {
			removerContato(request,response);
		}
		else if(requisicao.equals("/report")) {
			gerarRelatorio(request,response);
		}
		else {
			response.sendRedirect("index.html");
		}
		
		
	}
	
	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		//Criando um objeto que irá receber os dados da Classe JavaBeans.
		ArrayList<JavaBeans> lista = dao.listarContatos();
		//Encaminhamento do objeto lista ao documento agenda.jsp
		
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);  //request junto com resposta
		
		//teste de recebimento de lista.
		//for(int i = 0; i < lista.size(); i++) {
		//	System.out.println(lista.get(i).getIdcon());
		//	System.out.println(lista.get(i).getNome());
		//	System.out.println(lista.get(i).getFone());
		//	System.out.println(lista.get(i).getEmail());
		//	System.out.println("________________________________");
	//	}
		
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Editar contato
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recebimento do id de contato que sera editado.
		String idcon = request.getParameter("idcon");
		
		
		//System.out.println(idcon);
		contato.setIdcon(Integer.parseInt(idcon));
		
		//Executa o método selecionarContato pelo DAO.
		dao.selecaoContato(contato);
		//teste de recebimento
//		System.out.println(contato.getIdcon());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());
		
		//Settar os atributos ao formulario com o conteudo da classe javaBeans.
		request.setAttribute("idcon",contato.getIdcon());
		request.setAttribute("nome",contato.getNome());
		request.setAttribute("fone",contato.getFone());
		request.setAttribute("email",contato.getEmail());
		//Encaminhar ao documento Editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("Editar.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	/**
	 * Listade contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void ListadeContatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ListadeContatos.jsp");
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//gerar relatório em pdf
		Document documento = new Document();
		try {
			//tipo de conteúdo.
			response.setContentType("application/pdf");
			//nome do documento
			response.addHeader("Content-Disposition","inline; filename=" + "contatos.pdf");
			//criar o documento.
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir o documento -> conteúdo
			documento.open();
			//lista de contatos
			documento.add(new Paragraph("Lista de Contatos:"));
			//Listagem de Contato
			documento.add(new Paragraph(""));
			//Criar uma tabela no pfd.
			PdfPTable tabela = new PdfPTable(3);
			//cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//Popular a tabela com os contatos.
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i= 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
				
			}
			
			
			documento.add(tabela);
			documento.close();
			
		} catch (Exception e) {
			documento.close();
		}
		
	}
	
	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		contato.setIdcon(Integer.parseInt(idcon));
		dao.deletarContato(contato);
		response.sendRedirect("main");
		
		
		
		
		
	}
	
	
	
	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Teste de recebimento de dados do formulario novo.html
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		//response.sendRedirect("");
		//Setar os atributos da classe javabeans.
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invoca o método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		//redirecionar para a página agenda.jsp.
		response.sendRedirect("agenda.jsp");
	
	}
	
	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//teste de recebimento para a edição.
		/*System.out.println(request.getParameter("idcon"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));*/
		
		//settar os atributos do javaBeans 
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//executar o método alterarContato da classe DAO.
		dao.alterarContato(contato);
		//redirecionar para o documento agenda .jsp;(salvar a alteração)
		response.sendRedirect("main");
		
		
		
	}
}


