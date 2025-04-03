package dev.mcloudtw.shal;

public class IntVec3D {
    public int x;
    public int y;
    public int z;

    public IntVec3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "IntVec3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntVec3D)) return false;
        IntVec3D other = (IntVec3D) obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        result = 31 * result + Integer.hashCode(z);
        return result;
    }
}
