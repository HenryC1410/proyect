class NodoBPlus {
    int[] claves;
    int t;
    NodoBPlus[] hijos;
    boolean hoja;
    int n;

    NodoBPlus(int t, boolean hoja) {
        this.t = t;
        this.hoja = hoja;
        this.claves = new int[2 * t - 1];
        this.hijos = new NodoBPlus[2 * t];
        this.n = 0;
    }
}

class ArbolBPlus {
    NodoBPlus raiz;
    int t;

    ArbolBPlus(int t) {
        this.raiz = null;
        this.t = t;
    }

    void insertar(int clave) {
        if (raiz == null) {
            raiz = new NodoBPlus(t, true);
            raiz.claves[0] = clave;
            raiz.n = 1;
        } else {
            if (raiz.n == 2 * t - 1) {
                NodoBPlus nuevo = new NodoBPlus(t, false);
                nuevo.hijos[0] = raiz;
                dividirHijo(nuevo, 0, raiz);
                int i = 0;
                if (nuevo.claves[0] < clave) {
                    i++;
                }
                insertarNoLleno(nuevo.hijos[i], clave);
                raiz = nuevo;
            } else {
                insertarNoLleno(raiz, clave);
            }
        }
    }

    void dividirHijo(NodoBPlus x, int i, NodoBPlus y) {
        NodoBPlus z = new NodoBPlus(y.t, y.hoja);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            z.claves[j] = y.claves[j + t];
        }
        if (!y.hoja) {
            for (int j = 0; j < t; j++) {
                z.hijos[j] = y.hijos[j + t];
            }
        }
        y.n = t - 1;
        for (int j = x.n; j >= i + 1; j--) {
            x.hijos[j + 1] = x.hijos[j];
        }
        x.hijos[i + 1] = z;
        for (int j = x.n - 1; j >= i; j--) {
            x.claves[j + 1] = x.claves[j];
        }
        x.claves[i] = y.claves[t - 1];
        x.n++;
    }

    void insertarNoLleno(NodoBPlus x, int clave) {
        int i = x.n - 1;
        if (x.hoja) {
            while (i >= 0 && x.claves[i] > clave) {
                x.claves[i + 1] = x.claves[i];
                i--;
            }
            x.claves[i + 1] = clave;
            x.n++;
        } else {
            while (i >= 0 && x.claves[i] > clave) {
                i--;
            }
            if (x.hijos[i + 1].n == 2 * t - 1) {
                dividirHijo(x, i + 1, x.hijos[i + 1]);
                if (x.claves[i + 1] < clave) {
                    i++;
                }
            }
            insertarNoLleno(x.hijos[i + 1], clave);
        }
    }

    void recorrer() {
        if (raiz != null) {
            recorrer(raiz);
        }
    }

    void recorrer(NodoBPlus x) {
        int i;
        for (i = 0; i < x.n; i++) {
            if (!x.hoja) {
                recorrer(x.hijos[i]);
            }
            System.out.print(" " + x.claves[i]);
        }
        if (!x.hoja) {
            recorrer(x.hijos[i]);
        }
    }

    NodoBPlus buscar(int clave) {
        return (raiz == null) ? null : buscar(raiz, clave);
    }

    NodoBPlus buscar(NodoBPlus x, int clave) {
        int i = 0;
        while (i < x.n && clave > x.claves[i]) {
            i++;
        }
        if (i < x.n && clave == x.claves[i]) {
            return x;
        }
        if (x.hoja) {
            return null;
        }
        return buscar(x.hijos[i], clave);
    }
}
