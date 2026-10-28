package lab06;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import e3.chapter4.Card;
import e3.chapter4.CardSource;
import e3.chapter4.Rank;
import e3.chapter4.Suit;

public class TestBiCardSource {

	// Stub: một nguồn bài giả chứa tối đa một lá, để điều khiển chính xác
	// BiCardSource nhận được gì từ hai nguồn
	private static class CardSourceStub implements CardSource {
		private Optional<Card> aCard = Optional.empty();

		CardSourceStub() {}

		CardSourceStub(Card pCard) {
			aCard = Optional.of(pCard);
		}

		public Card draw() {
			assert !isEmpty();
			Card card = aCard.get();
			aCard = Optional.empty();
			return card;
		}

		public boolean isEmpty() {
			return !aCard.isPresent();
		}
	}

	private static final Card ACE = Card.get(Rank.ACE, Suit.CLUBS);
	private static final Card TWO = Card.get(Rank.TWO, Suit.CLUBS);

	// Fixture: JUnit tạo đối tượng test mới cho mỗi test nên ba stub luôn "tươi"
	private CardSource aEmpty = new CardSourceStub();
	private CardSource aContainsAce = new CardSourceStub(ACE);
	private CardSource aContainsTwo = new CardSourceStub(TWO);

	// ---- isEmpty() ----

	@Test
	void testEmpty_True() {
		assertTrue(new BiCardSource(aEmpty, aEmpty).isEmpty());
	}

	@Test
	void testEmpty_False_FirstEmpty() {
		assertFalse(new BiCardSource(aEmpty, aContainsAce).isEmpty());
	}

	@Test
	void testEmpty_False_SecondEmpty() {
		assertFalse(new BiCardSource(aContainsAce, aEmpty).isEmpty());
	}

	@Test
	void testEmpty_False_NeitherEmpty() {
		assertFalse(new BiCardSource(aContainsAce, aContainsTwo).isEmpty());
	}

	// ---- draw() ----

	@Test
	void testDraw_FirstEmpty() {
		assertSame(ACE, new BiCardSource(aEmpty, aContainsAce).draw());
	}

	@Test
	void testDraw_SecondEmpty() {
		assertSame(ACE, new BiCardSource(aContainsAce, aEmpty).draw());
	}

	@Test
	void testDraw_NeitherEmpty() {
		assertSame(ACE, new BiCardSource(aContainsAce, aContainsTwo).draw());
	}
}