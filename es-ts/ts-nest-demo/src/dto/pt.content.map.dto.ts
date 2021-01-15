import { ApiProperty } from "@nestjs/swagger";

class PTContentMapDto {
  @ApiProperty({
    nullable: false,
    required: true,
  })
  map_type: number;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  osp_key: string;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  content_path: string;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  content_id: string;

  @ApiProperty({
    nullable: false,
    required: true,
  })
  content_revision: string;
}

export { PTContentMapDto };
