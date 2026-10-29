import java.util.*;

class Solution {
    List<Integer>[] g;
    int[] in, out, dep, val;
    int[][] up;
    int time;
    BIT bit;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                         String s, String[] queries) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        in = new int[n]; out = new int[n];
        dep = new int[n]; val = new int[n];
        time = 0;
        dfs(0, -1, s);

        int log = 1;
        while ((1 << log) <= n) log++;
        up = new int[log][n];
        for (int i = 0; i < n; i++) up[0][i] = (i == 0 ? 0 : par[i]);
        for (int j = 1; j < log; j++)
            for (int i = 0; i < n; i++)
                up[j][i] = up[j-1][up[j-1][i]];

        bit = new BIT(2 * n);
        for (int i = 0; i < n; i++) {
            bit.add(in[i], val[i]);
            bit.add(out[i], val[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] a = q.split(" ");
            int u = Integer.parseInt(a[1]);
            if (a[0].equals("update")) {
                int x = val[u], y = 1 << (a[2].charAt(0) - 'a');
                bit.add(in[u], x ^ y);
                bit.add(out[u], x ^ y);
                val[u] = y;
            } else {
                int v = Integer.parseInt(a[2]), l = lca(u, v);
                int m = bit.sum(in[u] + 1) ^ bit.sum(in[v] + 1) ^ val[l];
                ans.add((m & (m - 1)) == 0);
            }
        }
        return ans;
    }

    int[] par = new int[50001];

    void dfs(int u, int p, String s) {
        par[u] = p < 0 ? u : p;
        dep[u] = p < 0 ? 0 : dep[p] + 1;
        val[u] = 1 << (s.charAt(u) - 'a');
        in[u] = time++;
        for (int v : g[u]) if (v != p) dfs(v, u, s);
        out[u] = time++;
    }

    int lca(int u, int v) {
        if (dep[u] < dep[v]) { int t = u; u = v; v = t; }
        for (int j = up.length - 1; j >= 0; j--)
            if (dep[u] - (1 << j) >= dep[v]) u = up[j][u];
        if (u == v) return u;
        for (int j = up.length - 1; j >= 0; j--)
            if (up[j][u] != up[j][v]) {
                u = up[j][u]; v = up[j][v];
            }
        return par[u];
    }

    class BIT {
        int[] t;
        BIT(int n) { t = new int[n + 2]; }
        void add(int i, int x) {
            for (i++; i < t.length; i += i & -i) t[i] ^= x;
        }
        int sum(int i) {
            int x = 0;
            for (; i > 0; i -= i & -i) x ^= t[i];
            return x;
        }
    }
}