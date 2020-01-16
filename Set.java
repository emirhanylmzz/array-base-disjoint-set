/* 31.12.2019 22:53
 * @outher emirhanylmzz
 */
public class SetArray {
	public int[] array;
	public int length = 0; //fonksiyonu ilk kez çağırdığımızı anlamak için
	
	public void makeSet(int data) {
		//fonksiyon ilk kez çağrıldığında bu işlemler yapılır
		if(length == 0) {
			array = new int[data + 1];
			array[data] = -1;
			length++;
		}
		//seti oluşturulacak sayı dizinin uzunluğundan büyükse kopyalama işlemi yapılır
		else if(data >= array.length) {
			int[] temp = new int[data + 1];
			for(int i = 0; i < array.length; ++i) {
				temp[i] = array[i];
			}
			array = new int[temp.length];
			for(int i = 0; i < array.length; ++i) {
				array[i] = temp[i];
			}
			array[data] = -1;
		}
		else if(data < array.length) {
			array[data] = -1;
		}
	}
	public int findSet(int data) {
		if(array.length < data) {
			System.out.println("HATALI GİRİS!!");
			System.exit(0);
		}
		//gelen sayı rootsa
		if(array[data] < 0) {
			return data;
		}
		else {
			return findSet(array[data]);
		}
	}
	public boolean union(int data1, int data2) {
		if(data1 == data2) {
			return false;
		}
		
		//rootlar bulunur rankleri karşılaştırmak için
		int parent1 = findSet(data1);
		int parent2 = findSet(data2);
		
		//ranklar eşitse (set boyutları)
		if(array[parent1] == array[parent2]) {
			array[data1] = array[data1] - 1;
			array[data2] = data1;
		}
		//data1'in seti daha büyükse 
		else if(-array[parent1] > -array[parent2]) {
			array[data2] = data1;
		}
		else {
			array[data1] = data2;
		}
		return true;
	}
	public int getRank(int data) {
		return array[findSet(data)] * (-1) - 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SetArray set1 = new SetArray();
		set1.makeSet(0);
		set1.makeSet(1);
		set1.makeSet(2);
		set1.makeSet(3);
		set1.makeSet(4);
		set1.makeSet(5);
		
		set1.union(0, 1);
		set1.union(1, 2);
		set1.union(2, 3);
		set1.union(3, 4);
		set1.union(4, 5);
		
		for(int a : set1.array) {
			System.out.println(a);
		}
		System.out.println(set1.getRank(5));
		//NOT mesela 1,2,3,4 den sonra makeSet(8) deseydik dizide 5,6,7 inci yerlere 0 koyuyor 
		//bu onların bir set olduğu ve parentlarının 0 olduğu anlamına gelmiyor. Yani index 1 ve parentin 0 olmasıyla aynı durum değil
		//O arada kalan yerlere farklı bir sayı atılmalı veya belirtilmeli ama bunu yapmadım.
	}

}