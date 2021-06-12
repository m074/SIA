package ar.edu.itba.sia.Model;

public class Font {

    private int[][] m;

    public Font(int[] font, int height, int width) {
        this.m = new int[height][width];
        //armo la font pasando numeros a bits
        for (int i = 0; i < height; i++) {
            boolean[] bits = new boolean[5];
            for (int k=4; k >= 0; k--) {
                bits[4-k] = (font[i] & (1 << k)) != 0;
            }
            for (int j = 0; j < width; j++) {
                this.m[i][j] = bits[j] ? 1 : 0;
            }
        }
    }

    public int[][] getMatrix() {
        return m;
    }

    public void setMatrix(int[][] matrix) {
        this.m = matrix;
    }

    public int[] getFontAsArray(){
        int[] res = new int[m.length*m[0].length];
        int k=0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                res[k++] = m[i][j];
            }
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] k:m) {
            for (int i:k) {
                //dibujo font
                stringBuilder.append(i == 1 ? '*' : ' ').append(' ');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
