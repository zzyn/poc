import { EntityRepository, Repository } from "typeorm";
import { ContentMigrationMappingEntity } from ".";

@EntityRepository(ContentMigrationMappingEntity)
export class ContentMigrationMappingEntityRepository extends Repository<ContentMigrationMappingEntity> {
  async findMigratedMapping(
    ospKey: string,
    contentRevision: string,
  ): Promise<ContentMigrationMappingEntity | undefined> {
    return this.findOne({
      where: { ospKey, contentRevision },
    });
  }

  async findOneContentMeta(
    contentPath: string,
    mapType: number,
  ): Promise<ContentMigrationMappingEntity | undefined> {
    return this.findOne({
      where: { contentPath, mapType },
      order: { createdTimestamp: "DESC" },
    });
  }

  async findMigratedMappingByOspKey(
    ospKey: string,
  ): Promise<ContentMigrationMappingEntity | undefined> {
    return this.findOne({
      where: { ospKey },
      order: { contentRevision: "DESC" },
    });
  }

  async findMigratedMappings(
    contentIds: string[],
  ): Promise<ContentMigrationMappingEntity[]> {
    return this.find({
      where: contentIds.reduce<{ contentId: string }[]>((list, contentId) => {
        return list.concat({ contentId });
      }, []),
    });
  }
}
