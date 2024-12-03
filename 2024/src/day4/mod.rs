pub fn day4() {
    let part1_result = day4_part1(INPUT);
    println!("Day 4, Part 1: {}", part1_result);
    let part2_result = day4_part2(INPUT);
    println!("Day 4, Part 2: {}", part2_result);
}

fn day4_part1(input: &str) -> i32 {
    0
}

fn day4_part2(input: &str) -> i32 {
    0
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_day4_part1() {
        let input = include_str!("test_input.txt");
        assert_eq!(day4_part1(input), 0);
    }

    #[test]
    fn test_day4_part2() {
        let input = include_str!("test_input.txt");
        assert_eq!(day4_part2(input), 0);
    }
}

const INPUT: &str = include_str!("input.txt");