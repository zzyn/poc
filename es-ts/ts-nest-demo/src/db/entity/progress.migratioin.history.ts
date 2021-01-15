import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({ name: "progress_migration_history" })
class ProgressMigrationHistoryEntity {
  @PrimaryGeneratedColumn({ name: "id", type: "bigint" })
  id: number;

  @Column({ name: "student_id", type: "varchar", width: 50 })
  studentId: string;

  @Column({ name: "progress_test_key", type: "char", width: 36 })
  progressTestKey: string;

  @Column({ name: "general_test_id", type: "char", width: 36 })
  generalTestId: string;

  @Column({ name: "general_test_status", type: "int", width: 11 })
  generalTestStatus: number;

  @Column({ name: "migration_version", type: "int", width: 11 })
  migrationVersion: number;

  @Column({ name: "pt_instance_key", type: "char", width: 36 })
  ptInstanceKey: string;

  @Column({ name: "migrated_at", type: "timestamp" })
  migratedAt: Date;
}

export { ProgressMigrationHistoryEntity };
