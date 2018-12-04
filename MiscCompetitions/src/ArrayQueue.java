public class ArrayQueue {

	private int logSz;
	private Object[] data;

	public ArrayQueue() {
		data = new Object[10];
		logSz = 0;
	}

	public int size() {
		return logSz;
	}

	public boolean isEmpty() {
		return logSz == 0;
	}
	
	public void enqueue(Object x) {
		if (logSz == data.length) {
			resize();
		}
		data[logSz] = x;
		logSz++;
	}
	
	public Object dequeue() {
		Object[] temp = new Object[logSz];
		for (int i = 1; i < logSz; i++) {
			temp[i - 1] = data[i];
		}
		Object ret = data[0];
		data = temp;
		logSz--;
		return ret;
	}
	
	public Object peekFront() {
		return data[0];
	}

	private void resize() {
		Object[] bigger = new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			bigger[i] = data[i];
		data = bigger;
	}
}
