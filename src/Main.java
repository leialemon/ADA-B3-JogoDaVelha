//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.lang.String;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] tabuleiro = {{'_','_','_'}, {'_','_','_'}, {'_','_','_'}};
        boolean repetida;
        boolean vitoria = checarVitoria(tabuleiro);
        boolean empate = checarEmpate(tabuleiro);
        // Perguntar nome dos jogadores;
        System.out.println("Digite o nome do jogador X: ");
        String jogadorX = input.nextLine();
        System.out.println("Digite o nome do jogador 0: ");
        String jogador0 = input.nextLine();
        String[] jogadores = {jogador0, jogadorX};
        String jogada;
        int linha;
        int coluna;
        char marcador;
        // Mostrar placar (criar uma classe para salvar as pontuações?);
        // Escolher o primeiro jogador (aleatório? Criar alguma regra?);

        Random aleatorio = new Random();
        int primeiro = aleatorio.nextInt(2);

        System.out.println(jogadores[primeiro]+", você começa!");


        while (!vitoria && !empate){
            // loop do jogo
            System.out.println(jogadores[primeiro]+", sua vez!");
            jogada = input.nextLine();
            linha = Character.getNumericValue(jogada.charAt(0));
            coluna = Character.getNumericValue(jogada.charAt(1));
            marcador = jogada.charAt(2);
            repetida = checarRepetida(tabuleiro[linha][coluna]);
            tabuleiro[linha][coluna] = marcador;
            tabuleiroImprimir(tabuleiro);
            vitoria = checarVitoria(tabuleiro);
            empate = checarEmpate(tabuleiro);
            if (Objects.equals(jogadores[primeiro], jogadorX)){
                System.out.println(jogador0+", sua vez!");
            }
            else {
                System.out.println(jogadorX + ", sua vez!");
            }
            jogada = input.nextLine();
            linha = Character.getNumericValue(jogada.charAt(0));
            coluna = Character.getNumericValue(jogada.charAt(1));
            marcador = jogada.charAt(2);
            repetida = checarRepetida(tabuleiro[linha][coluna]);
            tabuleiro[linha][coluna] = marcador;
            tabuleiroImprimir(tabuleiro);
            vitoria = checarVitoria(tabuleiro);
            empate = checarEmpate(tabuleiro);
        }

        if (vitoria){
            System.out.println("Vitória!");
        }
        if (empate){
            System.out.println("Empate!");
        }

        do{
            switch (input.nextLine().toLowerCase()){
                case "h":
                    historia();
                    break;
                case "r":
                    regras();
                    break;
            }
            // Loop do jogo (método?):
            // 1 - Pedir a jogada da vez; - FEITO
            // 2 - Verificar a validade da jogada;
            // 3 - Verificar se a jogada é igual à alguma das anteriores; (checar se o elemento tabuleiro[i][j] é diferente de "_");
            // 4 - Aplicar a jogada; (modificar tabuleiro[i][j]);
            // 5 - Verificar se houve vencedor; - FEITO
            // 6 - Pedir a próxima jogada do outro jogador. - FEITO
        }
        while(!Objects.equals(input.next().toLowerCase(), "q"));

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

    // Método para imprimir as regras e comandos;
    public static void regras(){
        //Mensagem de boas-vindas
        // Regras e opções de comando;
        System.out.println();
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
        boolean valida = true;

        //checar validade do formato da jogada.

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
        }
        else {
            empate = false;
        }
        return empate;
    }


    // Método para checar se há vencedor;
    public static boolean checarVitoria(char[][] tabuleiro) {
        boolean vitoria = false;

        // checar se os caracteres são iguais nas 8 posições que fazem a vitória. Se sim, retornar vitoria true;
        // 6 das 8 possibilidades de vitória;
        for (int i = 0; i < tabuleiro.length; i++) {
            if (Objects.equals(tabuleiro[i][0], tabuleiro[i][1]) && Objects.equals(tabuleiro[i][0], tabuleiro[i][2])) {
                if (!Objects.equals(tabuleiro[i][0], '_')) { // condicional para dizer que vitoria é false se os caracteres iguais forem "_";
                    vitoria = true;
                }
            } else if (Objects.equals(tabuleiro[0][i], tabuleiro[1][i]) && Objects.equals(tabuleiro[0][i], tabuleiro[2][i])) {
                if (!Objects.equals(tabuleiro[0][i], '_')) {
                    vitoria = true;
                }
            }

            // 2 possibilidades restantes (diagonais);
            if (Objects.equals(tabuleiro[0][2], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][2], tabuleiro[2][0])) {
                if (!Objects.equals(tabuleiro[0][2], '_')) {
                    vitoria = true;
                }
            } else if (Objects.equals(tabuleiro[0][0], tabuleiro[1][1]) && Objects.equals(tabuleiro[0][0], tabuleiro[2][2])) {
                if (!Objects.equals(tabuleiro[0][0], '_')) {
                    vitoria = true;
                }
            }
        }
        return vitoria;
    }
}