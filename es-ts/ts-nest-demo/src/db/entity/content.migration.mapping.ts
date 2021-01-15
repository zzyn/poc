import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

export enum MapType {
  Activity = 1,
  UnitQuiz = 2,
  MidTermExam = 3,
  FinalExam = 4,
}

@Entity({ name: "content_migration_mapping" })
class ContentMigrationMappingEntity {
  @PrimaryGeneratedColumn({ name: "id", type: "bigint" })
  id: number;

  @Column({ name: "map_type", type: "int", width: 11 })
  mapType: MapType;

  @Column({ name: "osp_key", type: "char", width: 36 })
  ospKey: string;

  @Column({ name: "content_path", type: "varchar", width: 64 })
  contentPath: string;

  @Column({ name: "content_id", type: "char", width: 100 })
  contentId: string;

  @Column({ name: "content_revision", type: "char", width: 100 })
  contentRevision: string;

  @Column({ name: "created_by", type: "varchar", width: 200 })
  createdBy: string;

  @Column({ name: "created_timestamp", type: "timestamp" })
  createdTimestamp: Date;

  @Column({ name: "updated_by", type: "varchar", width: 200 })
  updatedBy: string;

  @Column({ name: "updated_timestamp", type: "timestamp" })
  updatedTimestamp: Date;
}

export { ContentMigrationMappingEntity };
