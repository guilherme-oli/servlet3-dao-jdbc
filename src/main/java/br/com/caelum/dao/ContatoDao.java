package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.bean.Contato;
import br.com.caelum.jdbc.ConnectionFactory;

public class ContatoDao {
	
	
	private Connection connection;

	public ContatoDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) throws SQLException {
		
		String sql = "insert into contato (nome, email, endereco, dataNascimento) values (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, contato.getNome());
		ps.setString(2, contato.getEmail());
		ps.setString(3, contato.getEndereco());
		ps.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
		
		ps.execute();
		ps.close();
		connection.close();
	}

}
