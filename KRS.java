import java.util.Scanner;
public class KRS {
    static String [][] dataMahasiswa = new String[20][4];
   
    static int [] sksmatkul = new int[20];
    static int data = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            // Menu Awal
            while (true) {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Tambah Data KRS");
                System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
                System.out.println("3. Analisis Data KRS");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");
                int pilihan = sc.nextInt();
                sc.nextLine();

                if (pilihan == 1) {
                    tambahDataKRS();
                } else if (pilihan == 2) {
                    tampilkrs();
                } else if (pilihan == 3) {
                    analisisDataKRS();
                } else if (pilihan == 4) {
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            }
    }

    // Fungsi tambah data
    static void tambahDataKRS() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Tambah Data KRS ===");

            // Input data mahasiswa
            System.out.print("Nama Mahasiswa: ");
            String nama = sc.nextLine();
            System.out.print("NIM: ");
            String nim = sc.nextLine();
            
            int totalSKS = 0;

            // Input data mata kuliah
            while (true) {
                System.out.print("Kode Mata Kuliah: ");
                String kodeMK = sc.nextLine();
                System.out.print("Nama Mata Kuliah: ");
                String namaMK = sc.nextLine();
                System.out.print("Jumlah SKS (1-3): ");
                int sks = sc.nextInt();
                sc.nextLine();

                dataMahasiswa[data][0]=nama;
                dataMahasiswa[data][1] = nim;
                dataMahasiswa[data][2] = kodeMK;
                dataMahasiswa[data][3]= namaMK;
                sksmatkul[data]= sks;

                totalSKS += sks;
                data++;

                // Validasi SKS
                if (sks < 1 || sks > 3) {
                    System.out.println("Jumlah SKS tidak valid! Harus antara 1 dan 3.");
                    continue;
                }

                if (totalSKS + sks > 24) {
                    System.out.println("Total SKS melebihi batas (24 SKS). Tidak dapat menambahkan mata kuliah ini.");
                    break;
                }
                System.out.println("Mata kuliah berhasil ditambahkan.");

                System.out.print("Tambah mata kuliah lain? (y/t): ");
                String tambahLagi = sc.nextLine();
                if (!tambahLagi.equalsIgnoreCase("y")) {
                    break;
                }
            }

            System.out.println("Total SKS yang diambil: " + totalSKS);

            System.out.print("\nKembali ke menu utama? (y/t): ");
            String kembaliMenu = sc.nextLine();
            if (kembaliMenu.equalsIgnoreCase("y")) {
                break;
            }
        }
    }
    public static void tampilkrs (){
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Tampilkan Daftar KRS Mahasiswa ---");
        System.out.print("Masukan NIM Mahasiswa: ");
        String nim = sc.nextLine();
        

        int totalsks = 0;
        boolean nimsiswa = false;
        System.out.println("Daftar KRS:");
        for (int i = 0; i < data; i++) {
            if (dataMahasiswa[i][1].equals(nim)) {
                nimsiswa = true;
                System.out.println("Nama mahasiswa: " + dataMahasiswa[i][0]);
                System.out.println("Kode mata kuliah: " + dataMahasiswa[i][2] + ", Mata kuliah: " + dataMahasiswa[i][3] + ", SKS: " + sksmatkul[i]);
                totalsks += sksmatkul[i];
            }
        }
        if (nimsiswa) {
            System.out.println("Total SkS: " + totalsks);
        }else{
            System.out.println("Data KRS tidak ada");
        }
    }
    public static void analisisDataKRS() {   
        boolean[] sudahDihitung = new boolean[20];
        int jumlahMahasiswaKurang20 = 0;
        for (int i = 0; i < data; i++) {
            if (!sudahDihitung[i]) {
                int totalSKS = 0;
                for (int j = i; j < data; j++) {
                    if (dataMahasiswa[i][1] == dataMahasiswa[j][1]) {
                        totalSKS += sksmatkul[j];
                        sudahDihitung[j] = true;
                    }
                }
                if (totalSKS < 20) {
                    jumlahMahasiswaKurang20++;
                }
            }
        }
        System.out.println("Jumlah mahasiswa dengan total SKS kurang dari 20: " + jumlahMahasiswaKurang20);
    }
}