import { EntityRepository, Repository } from "typeorm";
import { ProgressMigrationHistoryEntity } from "./entity";

@EntityRepository(ProgressMigrationHistoryEntity)
// eslint-disable-next-line max-len
export class ProgressMigrationHistoryEntityRepository extends Repository<ProgressMigrationHistoryEntity> {
  async findProgressMigrationHistory(
    progressTestKey: string,
    studentIds: string[],
  ): Promise<ProgressMigrationHistoryEntity[]> {
    return this.find({
      where: studentIds.reduce<
        { studentId: string; progressTestKey: string }[]
      >((list, studentId) => {
        return list.concat({ studentId, progressTestKey });
      }, []),
    });
  }
}
