package daoo;

public class Run {

	public static void main(String[] args) {
		CustomerDao dao = new CustomerDao();
		dao.getListCus(2, 20).forEach(System.out::println);

	}

}
