package com.case2;

import java.util.concurrent.CompletableFuture;

// 開發一個服務來預訂酒店與航班
// 我們希望這二個任務可以並行
// 當任務都都完成後給用戶一個確認
// 使用 CompletableFuture 非同步調用來執行任務
public class BookingService {

	public CompletableFuture<String> bookHotel() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Hotel Booked!";
		});
	}

	public CompletableFuture<String> bookFlight() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "Flight Booked!";
		});
	}

	public void bookTrip() {
		long start = System.currentTimeMillis();

		CompletableFuture<String> hotelFuture = bookHotel();
		CompletableFuture<String> flightFuture = bookFlight();

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(hotelFuture, flightFuture);

		combinedFuture.thenRun(() -> {
			try {
				System.out.println(hotelFuture.get());
				System.out.println(flightFuture.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			System.out.printf("Total time: %d ms%n", (end - start));
		}).join();

	}

	public static void main(String[] args) {
		// new BookingService().bookTrip();
		System.out.println("開始預訂");
		new Thread(() -> {
			BookingService bookingService = new BookingService();
			bookingService.bookTrip();
		}).start();
		System.out.println("做其他事情：聽音樂");
		System.out.println("做其他事情：寫專題");
		System.out.println("做其他事情：睡覺");
	}
}
