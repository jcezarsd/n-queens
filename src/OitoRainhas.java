public class OitoRainhas {

	protected Integer numRainha;
	protected int contador = 0;
	protected String texto = "";
	
	public OitoRainhas() {
		
	}

	public void solucionar() {
		boolean[][] tabuleiro = new boolean[numRainha][numRainha];
		solucionar(tabuleiro, 0);
	}

	private void solucionar(boolean[][] tabuleiro, int linha){

		final int size = tabuleiro.length;

		for (int coluna = 0; coluna < size; coluna++) {

			// Adiciona rainha na posição
			tabuleiro[linha][coluna] = true;

			// Verifica se a rainha pode ser atacada nessa posicao
			if (naoSofreAtaque(tabuleiro, linha, coluna)) {
				
				if (linha < size-1) {
					
					solucionar(tabuleiro, linha+1);
					
				} else {
					
					texto = texto + " Solucao numero "+ (contador+1) + ":" + System.getProperty("line.separator");
					imprimir(tabuleiro);
					this.contador++;
					
				}
				
			}

			// Se nao for possivel, remove a rainha desta posicao
			tabuleiro[linha][coluna] = false;
			
		}
		
	}

	private static boolean naoSofreAtaque(boolean[][] tabuleiro, int linha, int coluna) {
		
		final int tamanhoTabuleiro = tabuleiro.length;
		
		// Verifica se a posicao esta em local que pode ser atacado
		for (int k = 1; k <= linha; k++) {
			
			if (tabuleiro[linha-k][coluna] 
				|| ((coluna - k >= 0) && tabuleiro[linha - k][coluna - k]) 
				|| ((coluna + k <  tamanhoTabuleiro) && tabuleiro[linha - k][coluna + k]) )
				return false;
			
		}

		return true;
		
	}

	private void imprimir(boolean[][] tabuleiro) {

		final int tamanhoTabuleiro = tabuleiro.length;

		String separadorLinha = "+";
		for (int i = 0; i < tamanhoTabuleiro; i++)
			separadorLinha += "-----+";

		for (int r = 0; r < tamanhoTabuleiro; r++) {
			
			texto = texto + separadorLinha + System.getProperty("line.separator");
			texto = texto + "|";
			
			for (int c = 0; c < tamanhoTabuleiro; c++) {
				
				String q = tabuleiro[r][c] ? "X" : " ";
				texto = texto + "  " + q + "  |";
				
			}
			
			texto = texto + System.getProperty("line.separator");
			
		}
		
		texto = texto + separadorLinha + "\n\n" + System.getProperty("line.separator");
		
	}

	public String iniciar() {
		
		System.out.println("Sistema para resolver o problema das N Rainhas");
		
		this.numRainha = 8;
				
		this.solucionar();
		
		if (contador == 0)
			System.out.println("O problema de " + numRainha + " Rainhas nao possui solucoes");			
		else
			System.out.println("O algoritmo encontrou " + this.contador + " solucoes para o problema de " + numRainha + " Rainhas");

		String s = this.texto;
		this.texto = "";
		return s;
		
	}
	
}
