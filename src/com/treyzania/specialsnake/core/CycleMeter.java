package com.treyzania.specialsnake.core;

public class CycleMeter implements Runnable {

	public long lastTicking;
	
	private final boolean isFast;
	private Thread averagerThread;
	private float[] averageLatencyBuffer; // ~10-second buffer, change as requested.
	
	public CycleMeter(boolean isFast) {
		
		this.lastTicking = System.currentTimeMillis();
		
		this.isFast = isFast;
		if (!isFast) {
			
			this.averagerThread = new Thread(this, "CycleMeter-AveragerThread");
			this.averagerThread.start();
			
			averageLatencyBuffer = new float[10];
			
		}
		
	}
	
	/**
	 * Changes the length of the buffer, longer values are slightly more accurate, but will take more time to
	 * update each time, 
	 * This won't do much if you disabled the averaging system.
	 * 
	 * @param length
	 */
	public void setBufferLength(int length) {
		this.averageLatencyBuffer = new float[length]; 
	}
	
	public void updateTime() {
		this.lastTicking = System.currentTimeMillis();
	}
	
	public float getLatency() {
		return System.currentTimeMillis() - this.lastTicking;
	}
	
	/**
	 * Returns the estimated number up updates per second.
	 * 
	 * @return
	 */
	public float getUpdatesPerSecond_Fast() {
		return 1000 / (this.getLatency());
	}
	
	/**
	 * Returns an estimated number of updates per second, based on a ~10-second average.
	 * Only returns an actual averaged value if "isFast" is disabled.
	 * 
	 * @return
	 */
	public float getUpdatesPerSecond_Averaged() {
		
		float out = 0;
		
		if (!this.isFast) {
			
			float totalLatency = 0F;
			
			// Add them together, for reasons.
			for (float _latency : this.averageLatencyBuffer) {
				totalLatency += _latency;
			}
			
			// Divide and update output.
			out = (totalLatency / this.averageLatencyBuffer.length);
			
		} else {
			// If averaging isn't enabled, then just return the fast output.
			out = this.getUpdatesPerSecond_Fast();
		}
		
		return out;
		
	}
	
	@Override
	public void run() {
		
		/*
		 * Why do I feel like having this "while (true)" thing is a baaaaaad idea?
		 * I just hope I did this code properly.
		 */
		while (true) {
			
			float[] newAvgLB = new float[10]; 
			
			newAvgLB[0] = this.getUpdatesPerSecond_Fast();
			for (int i = 0; i < (this.averageLatencyBuffer.length - 1); i++) {
				newAvgLB[i + 1] = this.averageLatencyBuffer[i];
			}
			
			this.averageLatencyBuffer = newAvgLB;
			
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
}
