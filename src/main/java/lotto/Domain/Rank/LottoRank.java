package lotto.Domain.Rank;

import lotto.DTO.MatchResult;

public enum LottoRank implements PrizeTier {
    FIRST(6,2_000_000_000L),
    SECOND(5,30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4,50_000L),
    FIFTH(3,5_000L),
    NONE(0,0L);

    private final int matchScore;
    private final long prize;

    LottoRank(int matchScore, long prize) {
        this.matchScore = matchScore;
        this.prize = prize;
    }

    public static LottoRank rankOf(MatchResult matchResult) {
        if (matchResult.count() == FIRST.matchScore) return FIRST;
        if (matchResult.count() == SECOND.matchScore && matchResult.bonus()) return SECOND;
        if (matchResult.count() == THIRD.matchScore) return THIRD;
        if (matchResult.count() == FOURTH.matchScore) return FOURTH;
        if (matchResult.count() == FIFTH.matchScore) return FIFTH;
        return NONE;
    }

    public long getPrize() {
        return prize;
    }
}
