package lab06;

import e3.chapter4.Card;
import e3.chapter4.CardSource;

//Đã sửa bug của đề: bản gốc rút nhầm từ aSource2 khi nguồn 1 chưa rỗng
//(và rút từ aSource1 khi nguồn 1 rỗng). Bản này: nguồn 1 chưa rỗng thì
//rút từ nguồn 1, ngược lại rút từ nguồn 2.
class BiCardSource implements CardSource {

	private final CardSource aSource1;
	private final CardSource aSource2;

	public BiCardSource(CardSource pSource1, CardSource pSource2) {
		aSource1 = pSource1;
		aSource2 = pSource2;
	}

	/*
	 * Draws from source 1 if not empty, and from source 2 if source 1 is empty.
	 */
	@Override
	public Card draw() {
		if (!aSource1.isEmpty()) {
			return aSource1.draw();
		}
		else {
			return aSource2.draw();
		}
	}

	@Override
	public boolean isEmpty() {
		return aSource1.isEmpty() && aSource2.isEmpty();
	}
}