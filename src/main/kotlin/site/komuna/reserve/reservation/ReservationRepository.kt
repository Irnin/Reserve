package site.komuna.reserve.reservation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import site.komuna.reserve.reservation.model.ReservationEntity
import site.komuna.reserve.reservation.model.ReservationStatus
import java.time.OffsetDateTime

interface ReservationStatusCount {
    val status: ReservationStatus
    val count: Long
}

@Repository
interface ReservationRepository:
    JpaRepository<ReservationEntity, Long>,
    JpaSpecificationExecutor<ReservationEntity>
{
    @Query("""
        SELECT r 
        FROM ReservationEntity r 
        WHERE r.room.id = :roomId 
          AND r.startAt < :endAt 
          AND r.endAt > :startAt
          AND r.status NOT IN (
              site.komuna.reserve.reservation.model.ReservationStatus.CANCELLED,
              site.komuna.reserve.reservation.model.ReservationStatus.REJECTED
          )
    """)
    fun findOverlappingReservations(roomId: Long, startAt: OffsetDateTime, endAt: OffsetDateTime): List<ReservationEntity>

    fun findByStartAtBetween(startAt: OffsetDateTime, endAt: OffsetDateTime): List<ReservationEntity>

    @Query("SELECT r.status as status, COUNT(r.id) as count FROM ReservationEntity r GROUP BY r.status")
    fun countReservationsByStatus(): List<ReservationStatusCount>
}