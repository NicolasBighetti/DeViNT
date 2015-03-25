package musiquizz;

public class CountDownThread implements Runnable {

	private MultiPlayerGame mpg;
	
	private int tempsMS;
	
	private boolean isStopped;
	
	public CountDownThread(MultiPlayerGame mpg) {
		this.setMpg(mpg);
		tempsMS = 30000;
		isStopped = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		do
		{
			try {

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!isStopped)
			{
				mpg.getTimer().setValue(mpg.getTimer().getValue() - 1);
				tempsMS-=100;
			}
		}while(tempsMS != 0);
		
		mpg.hardReset();
	}

	/**
	 * @return the mpg
	 */
	public MultiPlayerGame getMpg() {
		return mpg;
	}

	/**
	 * @param mpg the mpg to set
	 */
	public void setMpg(MultiPlayerGame mpg) {
		this.mpg = mpg;
	}

	/**
	 * @return the tempsMS
	 */
	public int getTempsMS() {
		return tempsMS;
	}

	/**
	 * @param tempsMS the tempsMS to set
	 */
	public void setTempsMS(int tempsMS) {
		this.tempsMS = tempsMS;
	}
	
	public void stop()
	{
		isStopped = true;
	}
	
	public void resume()
	{
		isStopped = false;
	}

}
