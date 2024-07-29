//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.lang.String;

public class Main {

    static int pontuacaoX;
    static int pontuacao0;
    static char[][] tabuleiro = {{'_','_','_'}, {'_','_','_'}, {'_','_','_'}};
    static Scanner input = new Scanner(System.in);
    static String jogadorX;
    static String jogador0;

    public static void main(String[] args) {

        // boas vindas
        historia();
        regras();
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
        // Perguntar nome dos jogadores;
        do{
        System.out.println("Digite o nome do jogador X: ");
        jogadorX = input.next();
        System.out.println("Digite o nome do jogador 0: ");
        jogador0 = input.next();
        if (Objects.equals(jogadorX, "")){
            System.out.println("Por favor, digite um nome para o jogador X.");
        } else if (Objects.equals(jogador0,"")) {
            System.out.println("Por favor, digite um nome para o jogador 0.");
        } else if (Objects.equals(jogador0, jogadorX)) {
            System.out.println("Por favor, digite nomes diferentes para os jogadores.");
        }
        } while(Objects.equals(jogadorX,"") || Objects.equals(jogador0,"") || Objects.equals(jogadorX,jogador0));

        String[] jogadores = {jogador0, jogadorX};

        // Escolher o primeiro jogador;
        Random aleatorio = new Random();
        int primeiro = aleatorio.nextInt(2);

        System.out.println(jogadores[primeiro]+", você começa!");

        String[] jogadoresOrdenados = new String[2];
        jogadoresOrdenados[0] = jogadores[primeiro];
        if(Objects.equals(jogadores[primeiro],jogador0)){
            jogadoresOrdenados[1] = jogadorX;
        }
        else{
            jogadoresOrdenados[1] = jogador0;
        }

        String comando;
        do{
            loopJogo(jogadoresOrdenados);
            System.out.println();
            pontuacao();
            System.out.println();
            limparTabuleiro();
            System.out.println("Digite 'h' para ler novamente a história do jogo, 'r' para ler as regras e comandos, ou 'q' para sair.");
            System.out.println("Para jogar novamente, digite qualquer coisa (exceto 'q').");
            comando = input.next().toLowerCase();
            switch (comando){
                case "h":
                    historia();
                    break;
                case "r":
                    regras();
                    break;
            }
        }
        while(!Objects.equals(comando, "q"));
    }
    // Método para contar a historinha;
    public static void historia(){
        Scanner input = new Scanner(System.in);
        System.out.println("No distante reino dos caracteres, o rei ASCII organizou um torneio para descobrir quem era o" +
                " melhor de seus guerreiros.");
        System.out.println("Diversas provas foram disputadas: justa, liça, concurso de comer tortas...");
        System.out.println("E um a um os bravos guerreiros foram sendo eliminados.");
        System.out.println("Entretanto, dois deles continuavam de pé, empatados no primeiro lugar de todas as provas"+
                " que disputaram.");
        System.out.println("Seus nomes eram Xena Xavier e 0strogildo Ostrogodo: X e 0.");
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
        System.out.println("Agora Xena e 0strogildo se encaram num desafio final.");
        System.out.println("O vencedor será conhecido como o melhor guerreiro dos caracteres.");
        System.out.println("----");
        System.out.println("X vs. 0: O confronto");
        System.out.println("----");
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
    }

    // Método para imprimir as regras e comandos;
    public static void regras(){
        //Mensagem de boas-vindas
        // Regras e opções de comando;
        System.out.println("Este é o jogo da velha. Na sua vez, cada jogador poderá marcar 'X' ou '0' em um espaço do tabuleiro.");
        System.out.println("O jogador que conseguir marcar seu símbolo em 3 casas consecutivas ganhará a partida.");
        tabuleiroImprimir();
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
        System.out.println("Para registrar sua jogada, o jogador da vez deverá digitar uma sequência de 3 caracteres:");
        System.out.println("LCM - a Linha da jogada, a Coluna da jogada, e o Marcador que deseja registrar.");
        System.out.println("Por exemplo: 11X marca um X no espaço central do tabuleiro.");
        System.out.println("Não é permitido repetir jogadas (ou seja, tentar marcar um espaço que já está marcado).");
        System.out.println("Se todos os espaços forem preenchidos e não houver vencedor, a partida acabará em empate.");
        System.out.println("Quando uma partida acabar, digite 'h' para ler novamente a história do jogo, 'r' para ler as regras e comandos, ou 'q' para sair.");
        System.out.println();
    }

    // Método para imprimir pontuações;
    public static void pontuacao(){
        System.out.println("Pontuação:");
        System.out.println("X "+jogadorX+": "+pontuacaoX);
        System.out.println("0 "+jogador0+": "+pontuacao0);
    }

    // Dois métodos que controlam o funcionamento do jogo;
    public static void loopJogo(String[] jogadores){
        boolean vitoria = checarVitoria();
        boolean empate = checarEmpate();
        tabuleiroImprimir();
        while (!vitoria && !empate){
            // loop do jogo
            for (String jogador : jogadores) {
                System.out.println(jogador + ", sua vez!");
                tabuleiro = loopJogada();
                vitoria = checarVitoria();
                empate = checarEmpate();
                if (vitoria || empate){
                    break;
                }
            }
        }
    }

    public static char[][] loopJogada(){
        String jogada;
        int linha;
        int coluna;
        char marcador;
        boolean validade;
        boolean repetida;
        do {
            jogada = input.next().toUpperCase();
            validade = checarValidade(jogada);
            while (!validade) {
                System.out.println("Jogue novamente:");
                jogada = input.next().toUpperCase();
                validade = checarValidade(jogada);
            }
            linha = Character.getNumericValue(jogada.charAt(0));
            coluna = Character.getNumericValue(jogada.charAt(1));
            marcador = jogada.charAt(2);
            repetida = checarRepetida(tabuleiro[linha][coluna]);
        }while(repetida);
        tabuleiro[linha][coluna] = marcador;
        tabuleiroImprimir();
        return tabuleiro;
    }

    // Método para verificar se a jogada é repetida;
    public static boolean checarRepetida(char jogada){
        boolean repetida;
        if (jogada != '_'){
            repetida = true;
            System.out.println("Jogada repetida, jogue novamente:");
        }
        else{
            repetida = false;
        }
        return repetida;
    }

    // Método para checar a validade da jogada;
    public static boolean checarValidade(String jogada){
        boolean valida;
        if (jogada.length() != 3){
            valida = false;
            System.out.println("Inválida! Sua jogada deve ter apenas 3 caracteres no formato LCM (Linha, Coluna, Marcador).");
        } else if (jogada.charAt(0) != '0' && jogada.charAt(0)!= '1' && jogada.charAt(0) != '2') {
            valida = false;
            System.out.println("Inválida! Insira um número de linha válido (0, 1 ou 2)");
        } else if (jogada.charAt(1) != '0' && jogada.charAt(1)!= '1' && jogada.charAt(1) != '2') {
            valida = false;
            System.out.println("Inválida! Insira um número de coluna válido (0, 1 ou 2)");
        } else if (jogada.charAt(2) != 'X' && jogada.charAt(2) != '0') {
            valida = false;
            System.out.println("Inválida! Seu marcador deve ser 'X' ou '0'");
        }
        else {
            valida = true;
        }
        return valida;
    }

    // Método para mostrar o tabuleiro;
    public static void tabuleiroImprimir(){
        char[] coordenadas = {'0','1','2'};
        // TABULEIRO
        System.out.println("  0 1 2");
        for(int i = 0; i< tabuleiro.length; i++){
            System.out.print(coordenadas[i] + " ");
            for(int j = 0; j< tabuleiro[i].length;j++){
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        // TABULEIRO
    }

    // Método para checar se o jogo acabou por empate;
    public static boolean checarEmpate() {
        boolean empate;
        int vazio = 0;
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == '_') {
                    vazio++;
                }
            }
        }
        if (vazio == 0){
            empate = true;
            System.out.println("Empate!");
        }
        else {
            empate = false;
        }
        return empate;
    }


    // Método para checar se há vencedor;
    public static boolean checarVitoria() {
        boolean vitoria = false;

        // (refatorar para diminuir tanta repetição de código e aumentar eficiência.)
        // 6 das 8 possibilidades de vitória;
        for (int i = 0; i < tabuleiro.length; i++) {
            if (Objects.equals(tabuleiro[i][0], tabuleiro[i][1]) && Objects.equals(tabuleiro[i][0], tabuleiro[i][2])) {
                if (!Objects.equals(tabuleiro[i][0], '_')) { // condicional para dizer que vitoria é false se os caracteres iguais forem "_";
                    vitoria = true;
                    if (tabuleiro[i][0] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                        pontuacaoX++;
                    } else if (tabuleiro[i][0] == '0') {
                        System.out.println("Vitória de "+jogador0);
                        pontuacao0++;
                    }
                }
            } else if (Objects.equals(tabuleiro[0][i], tabuleiro[1][i]) && Objects.equals(tabuleiro[0][i], tabuleiro[2][i])) {
                if (!Objects.equals(tabuleiro[0][i], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][i] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                        pontuacaoX++;
                    } else if (tabuleiro[0][i] == '0') {
                        System.out.println("Vitória de "+jogador0);
                        pontuacao0++;
                    }
                }
            }

            // 2 possibilidades restantes (diagonais);
            if (Objects.equals(tabuleiro[0][2], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][2], tabuleiro[2][0])) {
                if (!Objects.equals(tabuleiro[0][2], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][2] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                        pontuacaoX++;
                    } else if (tabuleiro[0][2] == '0') {
                        System.out.println("Vitória de "+jogador0);
                        pontuacao0++;
                    }
                }
            } else if (Objects.equals(tabuleiro[0][0], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][0], tabuleiro[2][2])) {
                if (!Objects.equals(tabuleiro[0][0], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][0] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                        pontuacaoX++;
                    } else if (tabuleiro[0][0] == '0') {
                        System.out.println("Vitória de "+jogador0);
                        pontuacao0++;
                    }
                }
            }
        }
        return vitoria;
    }

    public static void limparTabuleiro(){
        for (char[] i : tabuleiro) {
            Arrays.fill(i, '_');
        }
    }
    //
}