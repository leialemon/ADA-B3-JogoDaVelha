//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.lang.String;

public class Main {
    public static void main(String[] args) {
        // Variáveis
        Scanner input = new Scanner(System.in);
        char[][] tabuleiro = {{'_','_','_'}, {'_','_','_'}, {'_','_','_'}};
        boolean empate = checarEmpate(tabuleiro);

        // Perguntar nome dos jogadores;
        System.out.println("Digite o nome do jogador X: ");
        String jogadorX = input.nextLine();
        System.out.println("Digite o nome do jogador 0: ");
        String jogador0 = input.nextLine();
        String[] jogadores = {jogador0, jogadorX};

        boolean vitoria = checarVitoria(tabuleiro, jogadorX, jogador0);
        // Mostrar placar (criar uma classe para salvar as pontuações?); !!!!!!!!!

        // Escolher o primeiro jogador;
        Random aleatorio = new Random();
        int primeiro = aleatorio.nextInt(2);

        System.out.println(jogadores[primeiro]+", você começa!");

        do{
            while (!vitoria && !empate){
                // loop do jogo
                System.out.println(jogadores[primeiro]+", sua vez!");
                tabuleiro = jogo(tabuleiro);
                vitoria = checarVitoria(tabuleiro, jogadorX, jogador0);
                empate = checarEmpate(tabuleiro);
                if (Objects.equals(jogadores[primeiro], jogadorX)){
                    System.out.println(jogador0+", sua vez!");
                }
                else {
                    System.out.println(jogadorX + ", sua vez!");
                }
                tabuleiro = jogo(tabuleiro);
                vitoria = checarVitoria(tabuleiro, jogadorX, jogador0);
                empate = checarEmpate(tabuleiro);
            }

            // Verificar de quem foi a vitória e adicionar na pontuação; !!!!!!!!
            System.out.println("Digite 'h' para ler novamente a história do jogo, 'r' para ler as regras e comandos, ou 'q' para sair.");
            System.out.println("Para jogar novamente, digite qualquer coisa (exceto 'h', 'r' ou 'q')");
            switch (input.nextLine().toLowerCase()){
                case "h":
                    historia();
                    break;
                case "r":
                    regras();
                    break;
            }
        }
        while(!Objects.equals(input.next().toLowerCase(), "q"));
        // Loop do jogo (método?):
        // 1 - Pedir a jogada da vez; - FEITO
        // 2 - Verificar a validade da jogada; - FEITO
        // 3 - Verificar se a jogada é igual à alguma das anteriores; (checar se o elemento tabuleiro[i][j] é diferente de "_"); - FEITO
        // 4 - Aplicar a jogada; (modificar tabuleiro[i][j]); - FEITO
        // 5 - Verificar se houve vencedor; - FEITO
        // 6 - Pedir a próxima jogada do outro jogador. - FEITO
        // jogo deve continuar até alguém pressionar 'q';

    }
    // Método para contar a historinha;
    public static void historia(){
        Scanner input = new Scanner(System.in);
        System.out.println("No distante reino dos caracteres, o rei ASCII organizou um torneio para descobrir quem era o" +
                " melhor de seus guerreiros.");
        System.out.println();
        System.out.println("Diversas provas foram disputadas: justa, liça, concurso de comer tortas...");
        System.out.println("E um a um os bravos guerreiros foram sendo eliminados.");
        System.out.println("Entretanto, dois deles continuavam de pé, empatados no primeiro lugar de todas as provas"+
                " que disputaram.");
        System.out.println("Seus nomes eram Xena Xavier e Ostrogildo Ostrogodo: X e O.");
        System.out.println();
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
        System.out.println();
        System.out.println("Agora Xena e Ostrogildo se encaram num desafio final.");
        System.out.println("O vencedor será conhecido como o melhor guerreiro dos caracteres.");
        System.out.println("----");
        System.out.println("X O : o confronto");
        System.out.println("----");
        System.out.println("(Pressione qualquer tecla para continuar)");
        input.next();
    }

    // Método para imprimir as regras e comandos; !!!!!!!
    public static void regras(){
        //Mensagem de boas-vindas
        // Regras e opções de comando;
        System.out.println();
    }

    // Loop principal do jogo;
    public static char[][] jogo(char[][] tabuleiro){
        Scanner entrada = new Scanner(System.in);
        String jogada = entrada.nextLine().toUpperCase();
        boolean validade = checarValidade(jogada);
        while(!validade){
            System.out.println("Jogue novamente");
            jogada = entrada.nextLine().toUpperCase();
            validade = checarValidade(jogada);
        }
        int linha = Character.getNumericValue(jogada.charAt(0));
        int coluna = Character.getNumericValue(jogada.charAt(1));
        char marcador = jogada.charAt(2);
        boolean repetida = checarRepetida(tabuleiro[linha][coluna]);
        while(repetida){
            System.out.println("Essa jogada já foi feita, jogue novamente.");
            jogada = entrada.nextLine();
            linha = Character.getNumericValue(jogada.charAt(0));
            coluna = Character.getNumericValue(jogada.charAt(1));
            marcador = jogada.charAt(2);
            repetida = checarRepetida(tabuleiro[linha][coluna]);
        }
        tabuleiro[linha][coluna] = marcador;
        tabuleiroImprimir(tabuleiro);
        return tabuleiro;
    }

    // Método para verificar se a jogada é repetida;
    public static boolean checarRepetida(char jogada){
        boolean repetida;
        if (jogada != '_'){
            repetida = true;
        }
        else{
            repetida = false;
        }
        return repetida;
    }

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
    public static void tabuleiroImprimir(char[][] tabuleiro){
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

    public static boolean checarEmpate(char[][] tabuleiro) {
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
    public static boolean checarVitoria(char[][] tabuleiro, String jogadorX, String jogador0) {
        boolean vitoria = false;

        // Checar se os caracteres são iguais nas 8 posições que fazem a vitória. Se sim, retornar vitoria true;
        // 6 das 8 possibilidades de vitória;
        for (int i = 0; i < tabuleiro.length; i++) {
            if (Objects.equals(tabuleiro[i][0], tabuleiro[i][1]) && Objects.equals(tabuleiro[i][0], tabuleiro[i][2])) {
                if (!Objects.equals(tabuleiro[i][0], '_')) { // condicional para dizer que vitoria é false se os caracteres iguais forem "_";
                    vitoria = true;
                    if (tabuleiro[i][0] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                    } else if (tabuleiro[i][0] == '0') {
                        System.out.println("Vitória de "+jogador0);
                    }
                }
            } else if (Objects.equals(tabuleiro[0][i], tabuleiro[1][i]) && Objects.equals(tabuleiro[0][i], tabuleiro[2][i])) {
                if (!Objects.equals(tabuleiro[0][i], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][i] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                    } else if (tabuleiro[0][i] == '0') {
                        System.out.println("Vitória de "+jogador0);
                    }
                }
            }

            // 2 possibilidades restantes (diagonais);
            if (Objects.equals(tabuleiro[0][2], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][2], tabuleiro[2][0])) {
                if (!Objects.equals(tabuleiro[0][2], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][2] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                    } else if (tabuleiro[0][2] == '0') {
                        System.out.println("Vitória de "+jogador0);
                    }
                }
            } else if (Objects.equals(tabuleiro[0][0], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][0], tabuleiro[2][2])) {
                if (!Objects.equals(tabuleiro[0][0], '_')) {
                    vitoria = true;
                    if (tabuleiro[0][0] == 'X'){
                        System.out.println("Vitória de "+jogadorX);
                    } else if (tabuleiro[0][0] == '0') {
                        System.out.println("Vitória de "+jogador0);
                    }
                }
            }
        }
        return vitoria;
    }
    //
}