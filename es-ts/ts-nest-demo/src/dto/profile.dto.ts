import { ApiProperty } from "@nestjs/swagger";

class ProfileDto {
  @ApiProperty({
    nullable: false,
    required: true,
  })
  id: string;

  @ApiProperty({
    nullable: true,
    required: true,
    name: "first_name",
  })
  first_name: string;

  @ApiProperty({
    nullable: true,
    required: true,
    name: "last_name",
  })
  last_name: string;
}

export { ProfileDto };
