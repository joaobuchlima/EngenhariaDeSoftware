package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

public class Conexao {
    // Conexões com o banco de dados

    private static final String BASE_PATH = "http://www.buchmuller.com.br/engsoftware/";

    public static final String CREATE_URL = BASE_PATH + "inserirCliente.php";
    public static final String READ = BASE_PATH + "ListarCliente.php";
    public static final String UPDATE = BASE_PATH + "atualizarCliente.php";
    public static final String DELETE = BASE_PATH + "excluirCliente.php";

    public static final String GET_METHOD = "GET";
    static final String POST_METHOD = "POST";
}
