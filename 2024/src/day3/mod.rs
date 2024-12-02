pub fn day3() {
    let part1_result = day3_part1(INPUT);
    println!("Day 3, Part 1: {}", part1_result);
    let part2_result = day3_part2(INPUT);
    println!("Day 3, Part 2: {}", part2_result);
}

fn day3_part1(input: &str) -> i32 {
    todo!()
}

fn day3_part2(input: &str) -> i32 {
    todo!()
}

#[cfg(test)]
mod tests {
    use super::*;

    const TEST_INPUT: &str = include_str!("test_input.txt");

    #[test]
    fn test_day3_part1() {
        assert_eq!(day3_part1(TEST_INPUT), 0);
    }

    #[test]
    fn test_day3_part2() {
        assert_eq!(day3_part2(TEST_INPUT), 0);
    }
}

const INPUT: &str = include_str!("input.txt");