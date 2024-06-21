package com.case1;

// 開發一個服務來預訂機加酒
// 希望兩個任務並行
// 當任務完成後向使用者確認
public class BookingService {

	// 模擬預約飯店
	public String bookHotel() throws Exception {
		Thread.sleep(2000); // 模擬耗時操作
		return "Hotel Booked!";
	}

	// 模擬預約機票
	public String bookFlight() throws Exception {
		Thread.sleep(2000);
		return "Flight Booked!";
	}

	// 同時預約機加酒
	public void bookTrip() throws Exception {
		long start = System.currentTimeMillis();

		String hotel = bookHotel();
		System.out.println(hotel);

		String flight = bookFlight();
		System.out.println(flight);
		
		long end = System.currentTimeMillis();
		System.out.printf("Total time: %d ms%n", (end-start));
	}
	
	public static void main(String[] args) throws Exception {
		new BookingService().bookTrip();
	}
}
